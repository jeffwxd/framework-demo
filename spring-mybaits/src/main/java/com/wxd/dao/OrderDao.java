package com.wxd.dao;

import com.wxd.bo.OrderInfo;
import com.wxd.page.PageRequest;
import com.wxd.page.PageRequestParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/***
 *
 * @author xiongchuang
 * @date 2018-01-15
 */
@Mapper
public interface OrderDao {

    OrderInfo findById(Long id);

    List<OrderInfo> pageOrder(PageRequest pageRequest);

    List<OrderInfo> pageOrderParam(PageRequestParam pageRequest);

}
