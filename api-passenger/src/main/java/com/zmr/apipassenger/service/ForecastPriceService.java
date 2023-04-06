package com.zmr.apipassenger.service;

import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/6 21:41
 * @description 预估价格服务
 */
@Service
@Slf4j
public class ForecastPriceService {

    /**
     * 通过 出发地经纬度信息 && 目的地经纬度信息 计算最终的预估价格
     * @param depLongitude 出发地经度
     * @param depLatitude 出发地纬度
     * @param destLongitude 目的地经度
     * @param destLatitude 目的地纬度
     * @return 最终的预估价格
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        log.info("出发地经度:{}", depLongitude);
        log.info("出发地纬度:{}", depLatitude);
        log.info("目的地经度:{}", destLongitude);
        log.info("目的地纬度:{}", destLatitude);
        
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.23);
        
        log.info("最终计价信息为:{}", forecastPriceResponse.getPrice());
        
        return ResponseResult.success(forecastPriceResponse);
    }
}
