package com.zmr.apipassenger.controller;

import com.zmr.apipassenger.request.VerificationCodeDTO;
import com.zmr.apipassenger.service.VerificationCodeService;
import com.zmr.internalCommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 生成验证码信息
     * @param verificationCode 校验信息
     * @return
     */
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCode) {
        String passengerPhone = verificationCode.getPassengerPhone();
        System.out.println("接收到的电话号码为：" + passengerPhone);
        
        return verificationCodeService.generateVerificationCode(passengerPhone);
    }

    /**
     * 校验验证码信息
     * @param verificationCodeDTO 校验信息
     * @return
     */
    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        // 获取手机号和验证码信息
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();

        System.out.println("获取到的手机号:" + passengerPhone + ", 验证码: " + verificationCode);
        
        return verificationCodeService.checkCode(passengerPhone, verificationCode);
    }
}
