package com.wxd;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 16:05 2019/7/30
 */
@Configuration
@EnableFeignClients(basePackages = {"com.wxd.api",})
@ConditionalOnMissingClass(value = "com.wxd.ChildApplication")
public class ParentApplication {
}
