package com.zmr.internalCommon.util;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/5 11:04
 * @description Redis前缀工具类
 */
public class RedisPrefixUtils {
    
    /** 乘客验证码前缀 */
    public static String verificationCodePrefix = "passenger-verification-code-";

    /** token前缀 */
    public static String tokenPrefix = "token-";
    
    
    /**
     * 生成redis中key信息
     * @param passengerPhone 手机号
     * @return
     */
    public static String generatorKey(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 生成token中key信息
     * @param phone 用户手机号
     * @param identify 用户身份标识
     * @return
     */
    public static String generatorTokenKey(String phone, String identify, String tokenType) {
        return tokenPrefix + phone + "-" + identify + "-" + tokenType;
    }
}
