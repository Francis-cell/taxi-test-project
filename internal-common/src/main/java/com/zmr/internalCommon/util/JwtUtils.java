package com.zmr.internalCommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.collections.map.HashedMap;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/3 21:57
 * @description JWT工具类 JSON web 
 * 可以借助官方网站查看生成的token的信息---信息完全公开，所以里面不能存储比较私密的信息  https://jwt.io/
 */
public class JwtUtils {
    /** 盐 */
    public final static String SIGN = "asdfg@#$%^&";
    
    /** 关键KEY */
    public final static String JWT_KEY = "passengerPhone";
    
    /** 生成token */
    public static String generatorToken(String passengerPhone) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY, passengerPhone);

        // token过期时间
        Calendar calendar = Calendar.getInstance();
        // 设置过期时间为1天
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();
        
        // 创建JWT的builder
        JWTCreator.Builder builder = JWT.create();
        // 整合map
        map.forEach(
                (k, v) -> {
                    builder.withClaim(k, v);
                }
        );
        // 整合过期时间
        builder.withExpiresAt(date);
        
        // 生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        
        return sign;
    }
    
    /** 解析token */
    public static String parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        // 获取到JWT_KEY对应解析出来的值
        Claim claim = verify.getClaim(JWT_KEY);
        return claim.toString();
    }

    public static void main(String[] args) {
        String sign = generatorToken("17458458956");
        System.out.println("生成的token值:" + sign);

        String ans = parseToken(sign);
        System.out.println("解析后的token的值:" + ans);
    }
}
