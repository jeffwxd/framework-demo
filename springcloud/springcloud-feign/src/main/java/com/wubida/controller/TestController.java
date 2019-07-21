package com.wubida.controller;

import com.wubida.feign.TestFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestFeignService testFeign;

    @RequestMapping(value = "/test")
    public String test(){
        return testFeign.testByFeign();
    }
}
