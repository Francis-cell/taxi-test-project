package com.zmr.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/3 23:26
 * @description
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    
    /** 在初始化Spring Bean的时候手动初始化一下JWT拦截器 */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                // 拦截的路径(一般直接定义所有的路径)
                .addPathPatterns("/**")
                // 不拦截的路径(就是需要被排除在外的路径)
                .excludePathPatterns("/noAuthTest")
                // 获取验证码服务
                .excludePathPatterns("/verification-code")
                // 校验验证码服务
                .excludePathPatterns("/verification-code-check");
    }
}
