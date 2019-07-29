package com.wxd.service;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 11:37 2019/7/29
 */
public interface FeignService {

    @GetMapping("/hehe")
    String getName();

}
