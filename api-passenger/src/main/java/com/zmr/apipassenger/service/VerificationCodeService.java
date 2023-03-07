package com.zmr.apipassenger.service;

import com.zmr.apipassenger.remote.ServiceVerificationClient;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /** 乘客验证码前缀 */
    private String verificationCodePrefix = "passenger-verification-code-";
    
    public ResponseResult generateVerificationCode(String passengerPhone) {
        // 调用验证码服务，获取验证码    
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        System.out.println("获取到的验证码的值为：" + numberCode);

        
        
        // 存入Redis
        System.out.println("存入Redis");
        // key、value、过期时间
        String key = verificationCodePrefix + passengerPhone;
        // 存入Redis(设置key:numberCode的过期时间为2min)
        stringRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);

        // 返回值
        return ResponseResult.success("");
    }
}
