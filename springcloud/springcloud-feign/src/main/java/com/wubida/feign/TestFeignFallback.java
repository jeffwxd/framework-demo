package com.wubida.feign;

import org.springframework.stereotype.Component;

public class TestFeignFallback{
    public String testByFeign() {
        return "error000";
    }

}
