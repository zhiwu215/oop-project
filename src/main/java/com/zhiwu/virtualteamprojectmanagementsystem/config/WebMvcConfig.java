package com.zhiwu.virtualteamprojectmanagementsystem.config;

import com.zhiwu.virtualteamprojectmanagementsystem.interceptor.RoleGuardInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public RoleGuardInterceptor roleGuardInterceptor() {
        return new RoleGuardInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleGuardInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register", "/error", "/swagger-ui/**", "/v3/api-docs/**");
    }
}

