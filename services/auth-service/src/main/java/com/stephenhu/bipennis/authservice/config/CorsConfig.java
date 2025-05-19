package com.stephenhu.bipennis.authservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Stephen Hu
 * 解决跨域问题
 * @Date:2024/10/14
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // 一天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(MAX_AGE);
    }
}
