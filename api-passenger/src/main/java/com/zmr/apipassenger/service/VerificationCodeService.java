package com.zmr.apipassenger.service;

import com.zmr.apipassenger.remote.ServicePassengerUserClient;
import com.zmr.apipassenger.remote.ServiceVerificationClient;
import com.zmr.internalCommon.constant.CommonStatusEnum;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.request.VerificationCodeDTO;
import com.zmr.internalCommon.response.NumberCodeResponse;
import com.zmr.internalCommon.response.TokenResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
    private ServicePassengerUserClient servicePassengerUserClient;
    
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
        String key = generatorKey(passengerPhone);
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
        // ①、生成key
        String key = generatorKey(passengerPhone);
        // ②、根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中key对应的value值为：" + codeRedis);
        
        // 2、进行校验
        // 校验验证码
        // (StringUtils.isBlank()方法中，如果传参是" "的话，也是会返回true的; 
        // 而StringUtils.isEmpty()方法，则返回的仍然是false)
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VARIFICATION_CODE_ERROR.getCode(), 
                    CommonStatusEnum.VARIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VARIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VARIFICATION_CODE_ERROR.getValue());
        }
        
        
        // 3、如果原本没有用户信息，则先进行插入；如果原本有用户，则进行查询（进行远程服务的调用）
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);
        
        
        // 4、颁发token
        System.out.println("颁发token");
        
        // 设置token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token string");

        return ResponseResult.success(tokenResponse); 
    }

    /**
     * 生成redis中key信息
     * @param passengerPhone 手机号
     * @return
     */
    public String generatorKey(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }
}
