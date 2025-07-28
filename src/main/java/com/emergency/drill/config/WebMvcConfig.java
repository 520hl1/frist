package com.emergency.drill.config;

import com.emergency.drill.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 对所有路径生效
                .allowedOrigins("http://localhost:63342")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(true) // 允许携带Cookie（如需前端传Token和Cookie需开启）
                .maxAge(3600L); // 预检请求缓存时间（秒）
    }

    // JWT拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/api/**") // 拦截所有/api开头的接口
                .excludePathPatterns( // 放行无需登录的接口
                        "/api/sysUser/login",
                        "/api/sysUser/add",
                        "/api/sysDepartment/list" // 示例：若部门列表无需登录也可放行
                );
    }
}