package com.wubida.feign;

import org.springframework.stereotype.Component;

@Component
public class TestFeignFallback implements TestFeign {
    @Override
    public String testByFeign() {
        return "error";
    }

}
