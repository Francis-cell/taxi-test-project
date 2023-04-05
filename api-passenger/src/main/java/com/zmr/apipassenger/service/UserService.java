package com.zmr.apipassenger.service;

import com.zmr.internalCommon.dto.PassengerUser;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.dto.TokenResult;
import com.zmr.internalCommon.util.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/5 17:16
 * @description
 */
@Service
@Log4j2
public class UserService {
    public ResponseResult getUserByAccessToken(String accessToken) {
        // 1、解析accessToken，并获取到用户的手机号
        log.info("获取到的token的值为{}", accessToken);
        TokenResult tokenResult = JwtUtils.parseToken(accessToken);
        String phone = tokenResult.getPhone();
        
        // 2、根据手机号获取到用户的相关信息
        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("小黑");
        passengerUser.setProfilePhoto("O(∩_∩)O哈哈~");
        return ResponseResult.success(passengerUser);
    }
}
