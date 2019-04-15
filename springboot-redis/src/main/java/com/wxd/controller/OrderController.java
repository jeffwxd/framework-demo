package com.wxd.controller;

import com.wxd.model.OrderBo;
import com.wxd.page.Pagination;
import com.wxd.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/order")
@Api(description = "获取订单列表的信息")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/detail")
    @ApiOperation("获取所有明细")
    Pagination<OrderBo> getList(@RequestParam("beginTime") @ApiParam(value = "开始时间：yyyy-MM-dd hh:mm:ss", required = true) @NotNull String beginTime,
                                @RequestParam("endTime") @ApiParam(value = "结束时间：yyyy-MM-dd hh:mm:ss", required = true) @NotNull String endTime,
                                @RequestParam("page") @ApiParam(value = "页码", required = true) @NotNull @Min(0) Integer page,
                                @RequestParam("size") @ApiParam(value = "分页大小", required = true) @NotNull @Min(1) Integer size){
        return orderService.getList(beginTime,endTime,page,size);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
