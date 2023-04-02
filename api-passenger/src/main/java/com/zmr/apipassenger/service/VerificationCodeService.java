package com.zmr.apipassenger.service;

import com.zmr.apipassenger.remote.ServiceVerificationClient;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.response.NumberCodeResponse;
import com.zmr.internalCommon.response.TokenResponse;
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

    /**
     * 获取验证码信息服务
     * @param passengerPhone 用户手机号信息
     * @return
     */
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

    /**
     * 校验用户手机号+验证码的服务
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        // 1、根据手机号，从Redis中获取对应的验证码信息
        System.out.println("根据手机号，从Redis中获取对应的验证码信息");
        // 2、进行校验
        System.out.println("进行校验");
        // 3、如果原本没有用户信息，则先进行插入；如果原本有用户，则进行查询
        System.out.println("如果原本没有用户信息，则先进行插入；如果原本有用户，则进行查询");
        // 4、颁发token
        System.out.println("颁发token");
        
        // 设置token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token string");

        return ResponseResult.success(tokenResponse); 
    }
}
