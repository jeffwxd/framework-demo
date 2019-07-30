package com.wxd.api;

import com.wxd.constants.FeignParentConstant;
import com.wxd.service.FeignService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 11:37 2019/7/29
 */
@FeignClient(FeignParentConstant.SERVERNAME)
public interface FeignParentApi extends FeignService {
}
