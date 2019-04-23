package com.wxd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 15:25 2019/3/26
 */
@EnableTransactionManagement
@SpringBootApplication
public class LockAndTransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(LockAndTransactionApplication.class,args);
    }
}
