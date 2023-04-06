package com.zmr.servicePassengerUser.controller;

import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.request.VerificationCodeDTO;
import com.zmr.servicePassengerUser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/2 11:10
 * @description user插入服务
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    
    
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCode) {
        String passengerPhone = verificationCode.getPassengerPhone();
        log.info("乘客手机号：{}", passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }
    
    @GetMapping("/user/{phone}")
    public ResponseResult getUserByPhone(@PathVariable("phone") String passengerPhone) {
        log.info("乘客手机号：{}", passengerPhone);
        return userService.getUserByPhone(passengerPhone);
    }
}
