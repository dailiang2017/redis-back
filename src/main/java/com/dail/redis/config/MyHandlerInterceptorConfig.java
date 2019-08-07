package com.dail.redis.config;

import com.dail.redis.interceptors.RequestInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: dail
 * @CreateDate: 2019/8/7 18:17
 */
@Configuration
public class MyHandlerInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private RequestInterceptors requestInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptors);
    }
}
