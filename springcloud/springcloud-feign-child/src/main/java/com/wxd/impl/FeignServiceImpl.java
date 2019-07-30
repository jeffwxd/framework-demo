package com.wxd.impl;

import com.wxd.service.FeignService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 16:02 2019/7/30
 */
@Service
@RestController
public class FeignServiceImpl implements FeignService {

    @Override
    public String getName() {
        return "儿子";
    }
}
