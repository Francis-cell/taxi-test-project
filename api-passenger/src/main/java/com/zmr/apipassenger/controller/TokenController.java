package com.zmr.apipassenger.controller;

import com.zmr.apipassenger.service.TokenService;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/5 15:31
 * @description
 */
@RestController
public class TokenController {
    
    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        return tokenService.refreshToken(refreshTokenSrc);
    }
}
