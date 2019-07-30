package com.wxd.feign;

import com.wxd.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 17:46 2019/7/30
 */
@Service
public class TestFeign01 {
    @Autowired
    private FeignService feignService;

    public String getHello(){
        return feignService.getName();
    }
}
