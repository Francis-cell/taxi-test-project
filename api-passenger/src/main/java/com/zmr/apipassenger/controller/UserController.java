package com.zmr.apipassenger.controller;

import com.zmr.apipassenger.service.UserService;
import com.zmr.internalCommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/5 17:11
 * @description 用户信息获取服务
 */
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request) {
        // 1、获取到Token
        String token = request.getHeader("Authorization");
        // 2、获取用户的信息
        return userService.getUserByAccessToken(token);
    }
}
