package com.zmr.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.zmr.internalCommon.dto.ResponseResult;
import com.zmr.internalCommon.util.JwtUtils;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/3 23:08
 * @description JWT拦截器
 */
public class JwtInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        boolean result = true;
        String resultString = "";
        
        String token = request.getHeader("Authorization");
        // 解析token
        try {
            JwtUtils.parseToken(token);
        } 
        // 签名错误
        catch (SignatureVerificationException e) {
            resultString = "token sign error";
            result = false;
        }
        // token过期
        catch (TokenExpiredException e) {
            resultString = "token expired";
            result = false;
        }
        // 算法错误
        catch (AlgorithmMismatchException e) {
            resultString = "algorithm error";
            result = false;
        }
        // 其他异常情况
        catch (Exception e) {
            resultString = "token invalid";
            result = false;
        }
        
        // token校验失败情况处理
        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        
        return result;
    }
}
