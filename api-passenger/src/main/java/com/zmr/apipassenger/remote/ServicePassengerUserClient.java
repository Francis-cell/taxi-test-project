package com.zmr.apipassenger.remote;

import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/2 15:55
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {
    
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
