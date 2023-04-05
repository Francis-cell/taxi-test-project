package com.zmr.internalCommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zmr.internalCommon.dto.TokenResult;
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
    
    /** phone(因为 司机 & 乘客 都可以使用手机号进行注册登录) */
    public final static String JWT_KEY_PHONE = "phone";
    /** 身份标识 */
    public final static String JWT_KEY_IDENTITY = "identity";
    /** token类型 */
    public final static String JWT_TOKEN_TYPE = "tokenType";
    /** token时间，用于生成不同的token */
    public final static String JWT_TOKEN_TIME = "tokenTime";
    
    /** 生成token */
    public static String generatorToken(String passengerPhone, String identity, String tokenType) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_TOKEN_TYPE, tokenType);

        //// token过期时间
        //Calendar calendar = Calendar.getInstance();
        //// 设置过期时间为1天
        //calendar.add(Calendar.DATE, 1);
        //Date date = calendar.getTime();
        
        // 使用时间戳作为JWT_TOKEN_TIME的值，用于每次生成的token都生成不同的值
        map.put(JWT_TOKEN_TIME, Calendar.getInstance().getTime().toString());
        
        // 创建JWT的builder
        JWTCreator.Builder builder = JWT.create();
        // 整合map
        map.forEach(
                (k, v) -> {
                    builder.withClaim(k, v);
                }
        );
        // 整合过期时间
        //builder.withExpiresAt(date);
        
        // 生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        
        return sign;
    }
    
    /** 解析token */
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        // 获取到JWT_KEY对应解析出来的值(原本使用的是toString()方法，但是这样解析出来的结果，外面
        // 都会多出一对""，所以这里替换成asString()方法就可以了)
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }
    
    /** token check */
    public static TokenResult checkToken(String token) {
        TokenResult tokenResult = new TokenResult();
        // 解析token
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e) {
            return null;
        }
        return tokenResult;
    }
    

    public static void main(String[] args) {
        String sign = generatorToken("17458458956", "passenger", "accessToken");
        System.out.println("生成的token值:" + sign);

        TokenResult ans = parseToken(sign);
        System.out.println("解析后的token的值:" + ans.getPhone() + " " + ans.getIdentity());
    }
}
