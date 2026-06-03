package com.example.attendance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 临时注释掉，测试页面是否能访问
        // registry.addInterceptor(new AuthInterceptor())
        //         .addPathPatterns("/**")
        //         .excludePathPatterns("/user/login", "/user/register", "/login", "/register", "/students");
    }
}
