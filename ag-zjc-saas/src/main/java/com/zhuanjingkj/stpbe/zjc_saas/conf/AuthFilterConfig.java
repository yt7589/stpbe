package com.zhuanjingkj.stpbe.zjc_saas.conf;

import com.zhuanjingkj.stpbe.zjc_saas.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthFilterConfig {
    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}
