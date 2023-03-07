package com.zmr.apipassenger.remote;

import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/7 21:45
 * @description 验证码服务的客户端
 */

@FeignClient("service-vericationcode")
public interface ServiceVerificationClient {
    
    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/6")
    ResponseResult<NumberCodeResponse> getNumberCode();
}
