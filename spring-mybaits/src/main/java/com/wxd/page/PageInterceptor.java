package com.wxd.page;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chenboge on 2017/5/14.
 * <p>
 * Email:baigegechen@gmail.com
 * <p>
 * description:插件分页
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
@Component
public class PageInterceptor implements Interceptor {
    private static final String COUNT_TOTAL_NAME = "count_total";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = this.getActuralHandlerObject(invocation);
        BoundSql boundSql = statementHandler.getBoundSql();

        //判断是否是select语句
        if (!this.checkIsSelectFalg(boundSql)) {
            return false;
        }

        //获取分页参数
        PageRequest pageParam = this.getPageParam(boundSql);
        if (pageParam == null) {
            return invocation.proceed();
        }

        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);

        //获取总条数
        long totalElements = this.getTotalElements(invocation, metaStatementHandler, boundSql, pageParam);
        pageParam.setTotalElements(totalElements);

        //改变原始sql为分页sql
        return this.updateSql2Limit(invocation, metaStatementHandler, boundSql, pageParam);
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    /**
     * 在配置插件的时候配置默认参数
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }


    //    从代理对象中分离出真实statementHandler对象,非代理对象
    private StatementHandler getActuralHandlerObject(Invocation invocation) {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        Object object = null;
//        分离代理对象链，目标可能被多个拦截器拦截，分离出最原始的目标类
        while (metaStatementHandler.hasGetter("h")) {
            object = metaStatementHandler.getValue("h");
            metaStatementHandler = SystemMetaObject.forObject(object);
        }

        if (object == null) {
            return statementHandler;
        }
        return (StatementHandler) object;
    }


    /**
     * 判断是否是select语句，只有select语句，才会用到分页
     *
     * @return
     */
    private boolean checkIsSelectFalg(BoundSql boundSql) {
        String trimSql = boundSql.getSql().trim();
        return trimSql.toLowerCase().startsWith("select");
    }

    /**
     * 获取分页的参数
     * 参数可以通过map，@param注解进行参数传递。或者请求pojo继承自PageParam  将PageParam中的分页数据放进去
     */
    private PageRequest getPageParam(BoundSql boundSql) {


        Object paramerObject = boundSql.getParameterObject();
        if (paramerObject == null) {
            return null;
        }

        //通过map和@param注解将PageParam参数传递进来，pojo继承自PageParam  这里从参数中提取出传递进来的pojo继承自PageParam

        //继承方式 pojo继承自PageParam 只取出我们希望得到的分页参数
        if (paramerObject instanceof PageRequest) {
            return (PageRequest) paramerObject;
        }

        //处理传递进来的是map对象和通过注解方式传值的情况，从中提取出PageParam,循环获取map中的键值对，取出PageParam对象
        if (paramerObject instanceof Map) {
            Map params = (Map) paramerObject;
            for (Object value : params.values()) {
                if (value instanceof PageRequest) {
                    return (PageRequest) value;
                }
            }
        }

        return null;
    }


    /**
     * 获取当前sql查询的记录总数
     */
    private long getTotalElements(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, PageRequest pageParam) {
        //获取mapper文件中当前查询语句的配置信息
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        //获取所有配置Configuration
        org.apache.ibatis.session.Configuration configuration = mappedStatement.getConfiguration();

        //获取当前查询语句的sql
        String sql = boundSql.getSql();

        //将sql改写成统计记录数的sql语句
        //String countSql = "select count(" + pageParam.getCountKey() + ") as totle from (" + sql + ") $_paging";
        String countSql = this.getCountSql(sql, pageParam);

        //获取connection连接对象，用于执行countsql语句
        Connection conn = (Connection) invocation.getArgs()[0];

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //预编译统计总记录数的sql
            ps = conn.prepareStatement(countSql);
            //构建统计总记录数的BoundSql
            BoundSql countBoundSql = new BoundSql(configuration, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            //构建ParameterHandler，用于设置统计sql的参数
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
            //设置总数sql的参数
            parameterHandler.setParameters(ps);
            //执行查询语句
            rs = ps.executeQuery();
            if (rs.next()) {
                //与countSql中设置的别名对应
                return rs.getLong(COUNT_TOTAL_NAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0L;
    }

    /**
     * 获取 总条数sql
     */
    private String getCountSql(String sql, PageRequest pageParam) {
        //列包含子查询
        if (PageRequest.DEFAULT_COUNT_KEY_SUB.equals(pageParam.getCountKey())) {
            String countSql = "select count(1) as " + COUNT_TOTAL_NAME +
                    " from (" +
                    sql +
                    ") $_limit_sub_tab_name";
            return countSql;
        }

        int index = sql.toUpperCase().indexOf("FROM");
        return "select count(" + pageParam.getCountKey() + ") as " + COUNT_TOTAL_NAME +" "+ sql.substring(index);
    }

    /**
     * 修改原始sql语句为分页sql语句
     */
    private Object updateSql2Limit(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, PageRequest pageParam) throws InvocationTargetException, IllegalAccessException, SQLException {
        //构建新的分页sql语句
        String limitSql = boundSql.getSql() + " limit ?,?";
        //修改当前要执行的sql语句 也就是 boundSql.getSql()同时修改
        metaStatementHandler.setValue("delegate.boundSql.sql", limitSql);
        //相当于调用prepare方法，预编译sql并且加入参数，但是少了分页的两个参数，它返回一个PreparedStatement对象
        PreparedStatement ps = (PreparedStatement) invocation.proceed();
        //获取sql总的参数总数
        int count = ps.getParameterMetaData().getParameterCount();
        //设置与分页相关的两个参数
        ps.setInt(count - 1, pageParam.getPage() * pageParam.getSize());
        ps.setInt(count, pageParam.getSize());
        return ps;
    }

}