package com.zmr.apipassenger.controller;

import com.zmr.apipassenger.request.VerificationCodeDTO;
import com.zmr.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/5 17:06
 * @description 验证码获取接口
 */
@RestController
public class VerificationCodeController {
    
    @Autowired
    private VerificationCodeService verificationCodeService;
    
    
    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCode) {
        String passengerPhone = verificationCode.getPassengerPhone();
        System.out.println("接收到的电话号码为：" + passengerPhone);
        
        return verificationCodeService.generateVerificationCode(passengerPhone);
    }
}
