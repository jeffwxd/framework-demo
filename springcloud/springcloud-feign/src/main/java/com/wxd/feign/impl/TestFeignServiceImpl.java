package com.wxd.feign.impl;

import org.springframework.stereotype.Service;

/**
 * @author wxd
 * @date 2019/7/21 19:57
 */
@Service
public class TestFeignServiceImpl  {


    public String testByFeign() {
        return "hahaha";
    }
}
