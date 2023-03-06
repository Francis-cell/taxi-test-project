package com.zmr.serviceVerificationCode.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/6 22:33
 * @description
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "serviceVerification";
    }
}
