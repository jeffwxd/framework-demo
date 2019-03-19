package com.wxd.services;

import com.wxd.bo.OrderInfo;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 10:54 2019/3/18
 */
@Service
public interface OrderService {

    OrderInfo findById(@NotNull Long id);

    List<OrderInfo> pageOrder();
}
