package com.wxd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 17:13 2019/3/15
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "hello world";
    }
}
