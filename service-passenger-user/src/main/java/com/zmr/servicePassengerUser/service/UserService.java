package com.zmr.servicePassengerUser.service;

import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.servicePassengerUser.dto.PassengerUser;
import com.zmr.servicePassengerUser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/2 11:20
 * @description
 */
@Service
public class UserService {
    
    @Autowired
    private PassengerUserMapper passengerUserMapper;
    
    public ResponseResult loginOrRegister(String passengerPhone) {
        // 1、根据手机号查询用户信息
        System.out.println("根据手机号查询用户信息");

        HashMap<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size() == 0 ? "无记录" : passengerUsers.get(0).getPassengerName());

        // 2、判断用户信息是否存在
        System.out.println("判断用户信息是否存在");
        // 3、如果用户不存在，则进行注册，并向数据库插入用户信息
        System.out.println("如果用户不存在，则进行注册，并向数据库插入用户信息");
        return ResponseResult.success();
    }
}
