package com.zmr.serviceVerificationCode.controller;

import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/6 22:45
 * @description 获取服务验证码的Controller
 */
@RestController
public class NumberCodeController {
    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size) {

        System.out.println("size " + size);

        // 生成验证码
        double randomValue = (Math.random() * 9 + 1) * (Math.pow(10, size - 1));
        int randomCode = (int)randomValue;

        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(randomCode);

        return ResponseResult.success(response);
    }
}
