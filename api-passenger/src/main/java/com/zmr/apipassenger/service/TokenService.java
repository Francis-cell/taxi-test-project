package com.zmr.apipassenger.service;

import com.zmr.internalCommon.constant.CommonStatusEnum;
import com.zmr.internalCommon.constant.TokenConstants;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.dto.TokenResult;
import com.zmr.internalCommon.response.TokenResponse;
import com.zmr.internalCommon.util.JwtUtils;
import com.zmr.internalCommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/5 15:32
 * @description
 */
@Service
public class TokenService {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    public ResponseResult refreshToken(String refreshTokenSrc) {
        // 1、解析refreshToken
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (tokenResult == null) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();

        // 2、查询refreshToken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);

        // 3、校验refreshToken
        if ((StringUtils.isBlank(refreshTokenRedis) || !refreshTokenSrc.trim().equals(refreshTokenRedis.trim()))) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        
        // 4、生成双token
        String refreshToken = JwtUtils.generatorToken(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
        
        // 存储到Redis中，其中refreshToken的key已经生成了，还需要生成accessToken对应的key的值
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
        
        //stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        //stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 10, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 50, TimeUnit.SECONDS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        
        return ResponseResult.success(tokenResponse);
    }
}
