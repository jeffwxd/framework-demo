package com.wxd;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxd
 * @date 2019/7/21 18:15
 */
@Configuration
public class ConfigTest {
    @Autowired
    IClientConfig iClientConfig;

    @Bean
    public IRule ribbonRule(IClientConfig config){
        return  new RandomRule();
    }
}
