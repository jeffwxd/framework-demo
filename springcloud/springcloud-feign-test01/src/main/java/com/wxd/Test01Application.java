package com.wxd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 15:58 2019/7/22
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Test01Application {
    public static void main(String[] args) {
        SpringApplication.run(Test01Application.class,args);
    }
}
