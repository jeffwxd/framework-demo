package com.wxd.services.Impl;

import com.wxd.bo.OrderInfo;
import com.wxd.dao.OrderDao;
import com.wxd.page.PageRequest;
import com.wxd.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 10:56 2019/3/18
 */
@Service
public class OrderServiceImpl implements OrderService {


    private OrderDao orderDao;

    @Autowired
    public void OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderInfo findById(@NotNull Long id) {
        OrderInfo info = orderDao.findById(id);
        return info;
    }

    public List<OrderInfo> pageOrder() {
        PageRequest pageRequest = PageRequest.of(0,2);
        List<OrderInfo> infos = orderDao.pageOrder(pageRequest);
        if(CollectionUtils.isEmpty(infos)){
            return Collections.EMPTY_LIST;
        }
        return infos;
    }
}
