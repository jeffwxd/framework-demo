package com.wubida.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "springcloud-provider")
public interface TestFeignService {

    @GetMapping("/test")
    String testByFeign();

}
