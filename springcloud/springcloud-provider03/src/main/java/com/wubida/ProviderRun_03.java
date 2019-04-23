package com.wubida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 15:14 2019/2/28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderRun_03 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderRun_03.class,args);
    }
}
