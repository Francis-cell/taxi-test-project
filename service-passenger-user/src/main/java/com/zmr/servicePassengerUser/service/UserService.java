package com.zmr.servicePassengerUser.service;

import com.zmr.internalCommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/2 11:20
 * @description
 */
@Service
public class UserService {
    public ResponseResult loginOrRegister(String passengerPhone) {
        // 根据手机号查询用户信息
        System.out.println("根据手机号查询用户信息");
        // 判断用户信息是否存在
        System.out.println("判断用户信息是否存在");
        // 如果用户不存在，则进行注册，并向数据库插入用户信息
        System.out.println("如果用户不存在，则进行注册，并向数据库插入用户信息");
        return ResponseResult.success();
    }
}
