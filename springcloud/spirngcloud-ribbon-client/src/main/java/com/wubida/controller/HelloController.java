package com.wubida.controller;

import com.wubida.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxd
 * @date 2019/7/21 17:01
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/test")
    public String test() throws Exception {
        return helloService.hello();
    }
}
