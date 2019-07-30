package com.wxd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${server.port}")
    String port;

    @Value("${spring.application.name}")
    String name;
    @Value("${kd.app.name}")
    String appName;

    @RequestMapping("/test")
    public String test() {
        //int a = 1/0;
        return appName+"上线测试:"+name+":"+port;
    }
}