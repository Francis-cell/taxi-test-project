package com.zmr.internalCommon.dto;

import lombok.Data;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/3 22:37
 * @description Token实体类信息
 */
@Data
public class TokenResult {
    /** 手机号 */
    private String phone;
    /** 身份标识 */
    private String identity;
}
