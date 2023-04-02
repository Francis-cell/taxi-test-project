package com.zmr.apipassenger.request;

import lombok.*;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/5 17:08
 * @description 验证码请求时接收的实体类
 */
@Data
public class VerificationCodeDTO {
    /** 电话号码 */
    private String passengerPhone;
    /** 验证码 */
    private String verificationCode;
}
