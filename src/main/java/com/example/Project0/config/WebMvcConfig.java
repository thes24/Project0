package com.example.Project0.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Project0.interceptor.MemberInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MemberInterceptor logInInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(logInInterceptor)
                .excludePathPatterns("/api/member/signup")
                .excludePathPatterns("/api/member/login")
                .excludePathPatterns("/api/member/check-login")
                .addPathPatterns("/api/member/**")
                .addPathPatterns("/api/{boardId}")
                .addPathPatterns("/api/board/**");
    }
}
