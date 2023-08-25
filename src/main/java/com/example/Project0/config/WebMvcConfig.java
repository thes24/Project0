package com.example.Project0.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Project0.interceptor.LogInInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LogInInterceptor logInInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(logInInterceptor).addPathPatterns("/member/")
                .addPathPatterns("/{boardId}")
                .addPathPatterns("/board/**");
    }
}
