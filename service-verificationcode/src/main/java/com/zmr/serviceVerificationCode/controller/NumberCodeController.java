package com.zmr.serviceVerificationCode.controller;

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
    public JSONObject numberCode(@PathVariable("size") int size) {

        System.out.println("size" + size);
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");
        JSONObject data = new JSONObject();
        data.put("numberCode", 123456);
        result.put("data", data);

        return result;
    }
}
