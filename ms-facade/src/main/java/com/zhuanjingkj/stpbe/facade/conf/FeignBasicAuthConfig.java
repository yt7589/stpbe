package com.zhuanjingkj.stpbe.facade.conf;

import com.zhuanjingkj.stpbe.facade.interceptor.FeignBasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignBasicAuthConfig {
    @Bean
    public FeignBasicAuthRequestInterceptor feignBasicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }
}
