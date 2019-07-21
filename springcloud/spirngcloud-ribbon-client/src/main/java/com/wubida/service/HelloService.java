package com.wubida.service;

import com.netflix.client.http.HttpRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author wxd
 * @date 2019/7/21 17:02
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String hello() throws Exception{
        //Thread.sleep(1000);
        return restTemplate.getForObject("http://springcloud-provider/test",String.class);
    }
    public String helloFallBack(){
        return "error";
    }
}
