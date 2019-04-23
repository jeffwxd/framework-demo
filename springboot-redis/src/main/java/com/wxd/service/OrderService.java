package com.wxd.service;

import com.wxd.model.OrderBo;
import com.wxd.page.Pagination;

public interface OrderService {

     Pagination<OrderBo> getList(String beginTime, String endTime, Integer page, Integer size);
}
