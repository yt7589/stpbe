package com.zhuanjingkj.stpbe.user.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigure;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigure webMvcConfigure() {
        return new WebMvcConfigure() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*")
                    .allowedOrigins("*");
                }
        };
    }
}