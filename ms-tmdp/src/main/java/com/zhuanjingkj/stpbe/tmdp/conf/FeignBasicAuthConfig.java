package com.zhuanjingkj.stpbe.tmdp.conf;

import com.zhuanjingkj.stpbe.tmdp.interceptor.FeignBasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignBasicAuthConfig {
    @Bean
    public FeignBasicAuthRequestInterceptor feignBasicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }
}
