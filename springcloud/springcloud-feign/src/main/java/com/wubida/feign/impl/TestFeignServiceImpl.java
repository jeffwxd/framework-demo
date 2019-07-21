package com.wubida.feign.impl;

import com.wubida.feign.TestFeignService;
import org.springframework.stereotype.Service;

/**
 * @author wxd
 * @date 2019/7/21 19:57
 */
@Service
public class TestFeignServiceImpl implements TestFeignService {

    @Override
    public String testByFeign() {
        return "hahaha";
    }
}
