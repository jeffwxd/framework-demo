package com.wxd.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "springcloud-provider",fallback = TestFeignFallback.class)
public interface TestFeign {
    @RequestMapping(value = "/test")
    String testByFeign();

     class StringTest {
        public static void main(String[] args) {
            String m = "hello";
            String n = "hello";
            String u = new String(m);
            String v = new String("hello");

            System.out.println("m == n: " + (m == n));
            System.out.println("m == u: " + (m == u));
            System.out.println("m == v: " + (m == v));
        }
    }
}
