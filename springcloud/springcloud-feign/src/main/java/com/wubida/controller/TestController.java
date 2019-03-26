package com.wubida.controller;

import com.wubida.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestFeign testFeign;// 注入 Feign接口
    @RequestMapping(value = "/test")
    public String test(){
        return testFeign.testByFeign();
    }
}
