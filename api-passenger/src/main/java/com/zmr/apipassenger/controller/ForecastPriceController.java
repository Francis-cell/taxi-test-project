package com.zmr.apipassenger.controller;

import com.zmr.apipassenger.service.ForecastPriceService;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.request.ForecastPriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/6 21:29
 * @description 预估价格服务
 */
@RestController
@Slf4j
public class ForecastPriceController {
    
    @Autowired
    private ForecastPriceService forecastPriceService;
    
    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();
        
        log.info("调用计价服务获取预估价格");
        
        return forecastPriceService.forecastPrice(depLongitude, depLatitude, destLongitude, destLatitude);
    }
}
