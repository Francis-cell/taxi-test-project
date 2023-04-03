package com.zmr.apipassenger.controller;

import com.zmr.internalCommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/5 16:43
 * @description 测试Controller类
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test api passenger";
    }

    /**
     * 需要token的接口调用
     * @return
     */
    @GetMapping("/authTest")
    public ResponseResult authTest() {
        return ResponseResult.success("auth test!");
    }

    /**
     * 不需要token的接口调用
     * @return
     */
    @GetMapping("/noAuthTest")
    public ResponseResult noAuthTest() {
        return ResponseResult.success(" no auth test!");
    }
}
