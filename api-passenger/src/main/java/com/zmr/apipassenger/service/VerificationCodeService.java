package com.zmr.apipassenger.service;

import com.zmr.apipassenger.remote.ServiceVerificationClient;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/5 17:14
 * @description 生成验证码和存储到Redis的服务过程
 */
@Service
public class VerificationCodeService {
    
    @Autowired
    private ServiceVerificationClient serviceVerificationClient;
    
    public String generateVerificationCode(String passengerPhone) {
        // 调用验证码服务，获取验证码    
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode();
        int numberCode = numberCodeResponse.getData().getNumberCode();

        System.out.println("获取到的验证码的值为：" + numberCode);

        // 存入Redis
        System.out.println("存入Redis");
        
        // 创建JSON(在这里需要在pom.xml里面引入JSON)
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");

        // 返回值
        return result.toString();
    }
}
