package com.zmr.serviceVerificationCode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/3/6 22:31
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVerificationCode {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVerificationCode.class);
    }
}
