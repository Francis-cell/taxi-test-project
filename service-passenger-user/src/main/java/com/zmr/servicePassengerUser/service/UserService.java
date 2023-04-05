package com.zmr.servicePassengerUser.service;

import com.zmr.internalCommon.constant.CommonStatusEnum;
import com.zmr.internalCommon.dto.PassengerUser;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.servicePassengerUser.mapper.PassengerUserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/2 11:20
 * @description
 */
@Service
@Log4j2
public class UserService {
    
    @Autowired
    private PassengerUserMapper passengerUserMapper;
    
    public ResponseResult loginOrRegister(String passengerPhone) {
        // 1、根据手机号查询用户信息
        System.out.println("根据手机号查询用户信息");

        HashMap<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        
        
        // 2、判断用户信息是否存在
        System.out.println("判断用户信息是否存在");
        if (passengerUsers.size() == 0) {
            // 3、如果用户不存在，则进行注册，并向数据库插入用户信息
            System.out.println("如果用户不存在，则进行注册，并向数据库插入用户信息");
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("小黑");
            passengerUser.setPassengerGender((byte) 1);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte) 0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);
            
            passengerUserMapper.insert(passengerUser);
        }
        
        return ResponseResult.success();
    }

    /**
     * 通过用户手机号获取用户相关信息
     * @param passengerPhone 用户手机号
     * @return
     */
    public ResponseResult getUserByPhone(String passengerPhone) {
        // 先通过手机号查询到用户的信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        
        // 判断user是否存在
        if (passengerUsers.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXIST.getCode(), CommonStatusEnum.USER_NOT_EXIST.getValue());
        } else {
            // 获取用户的姓名和头像url信息，目前来说绝对存在在查询返回的passengerUsers列表中
            PassengerUser passengerUser = passengerUsers.get(0);
            log.info("返回的用户的姓名为:{}", passengerUser.getPassengerName());
            log.info("用户头像的url信息为:{}", passengerUser.getProfilePhoto());
            return ResponseResult.success(passengerUser);
        }
    }
}
