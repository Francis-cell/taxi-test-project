package com.zmr.internalCommon.constant;

import lombok.Getter;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/6 23:11
 * @description 通用请求状态枚举类型
 */
public enum CommonStatusEnum {
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
