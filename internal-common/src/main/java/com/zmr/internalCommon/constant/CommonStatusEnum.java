package com.zmr.internalCommon.constant;

import lombok.Getter;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/6 23:11
 * @description 通用请求状态枚举类型
 */
public enum CommonStatusEnum {

    /**
     * 验证码不正确 1000-1099
     */
    VARIFICATION_CODE_ERROR(1099, "验证码不正确"),

    /**
     * Token不正确 1100-1199
     */
    TOKEN_ERROR(1199, "token不正确"),

    /**
     * 用户相关信息 1200-1299
     */
    USER_NOT_EXIST(1200, "用户不存在"),



    /** 成功 */
    SUCCESS(1, "success"),
    /** 失败 */
    FAIL(0, "fail")
    ;

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
