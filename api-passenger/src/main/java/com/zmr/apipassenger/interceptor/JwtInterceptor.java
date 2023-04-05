package com.zmr.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.dto.TokenResult;
import com.zmr.internalCommon.util.JwtUtils;
import com.zmr.internalCommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/3 23:08
 * @description JWT拦截器
*/
public class JwtInterceptor implements HandlerInterceptor {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        boolean result = true;
        String resultString = "";
        
        String token = request.getHeader("Authorization");
        TokenResult tokenResult = null;
        // 解析token
        try {
            tokenResult = JwtUtils.parseToken(token);
        } 
        // 签名错误
        catch (SignatureVerificationException e) {
            resultString = "token sign error";
            result = false;
        }
        // token过期
        catch (TokenExpiredException e) {
            resultString = "token expired";
            result = false;
        }
        // 算法错误
        catch (AlgorithmMismatchException e) {
            resultString = "algorithm error";
            result = false;
        }
        // 其他异常情况
        catch (Exception e) {
            resultString = "token invalid";
            result = false;
        }
        
        // 校验token是否和Redis中存储的token值是一致的
        if (tokenResult == null) {
            resultString = "token invalid";
            result = false;
        } else {
            // 拼接key
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            
            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity);
            // 从redis中获取token
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            if (StringUtils.isBlank(tokenRedis)) {
                resultString = "token invalid";
                result = false;
            } else {
                if (!token.trim().equals(tokenRedis.trim())) {
                    resultString = "token invalid";
                    result = false;
                }
            }
        }
        
        
        
        // token校验失败情况处理
        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        
        return result;
    }
}
