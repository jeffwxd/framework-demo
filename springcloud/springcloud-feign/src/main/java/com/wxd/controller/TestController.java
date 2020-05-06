package com.wxd.controller;

import com.wxd.feign.TestFeign;
import com.wxd.feign.TestFeign01;
import com.wxd.feign.TestFeignService;
import com.wxd.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestFeign testFeign;

    @RequestMapping(value = "/test")
    public String test() {
        return testFeign.testByFeign01();
    }


}
