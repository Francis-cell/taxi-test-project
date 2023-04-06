package com.zmr.internalCommon.response;

import lombok.Data;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/6 21:46
 * @description 预估计价信息返回体信息
 */
@Data
public class ForecastPriceResponse {
    /** 预估计价信息 */
    private double price;
}
