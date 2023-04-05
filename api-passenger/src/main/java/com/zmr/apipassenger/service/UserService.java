package com.zmr.apipassenger.service;

import com.zmr.apipassenger.remote.ServicePassengerUserClient;
import com.zmr.internalCommon.dto.PassengerUser;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.dto.TokenResult;
import com.zmr.internalCommon.request.VerificationCodeDTO;
import com.zmr.internalCommon.util.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/5 17:16
 * @description
 */
@Service
@Log4j2
public class UserService {
    
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;
    
    public ResponseResult getUserByAccessToken(String accessToken) {
        // 1、解析accessToken，并获取到用户的手机号
        log.info("获取到的token的值为{}", accessToken);
        TokenResult tokenResult = JwtUtils.parseToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("获取到的用户的手机号信息为：{}", phone);
        
        // 2、根据手机号获取到用户的相关信息
        ResponseResult userByPhone = servicePassengerUserClient.getUserByPhone(phone);
        
        return ResponseResult.success(userByPhone.getData());
    }
}
