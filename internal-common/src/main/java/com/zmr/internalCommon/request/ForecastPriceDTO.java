package com.zmr.internalCommon.request;

import lombok.Data;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/6 21:32
 * @description 预估价格服务实体类
 */
@Data
public class ForecastPriceDTO {
    /** 出发地经度 */
    private String depLongitude;
    
    /** 出发地纬度 */
    private String depLatitude;
    
    /** 目的地经度 */
    private String destLongitude;
    
    /** 目的地纬度 */
    private String destLatitude;
}
