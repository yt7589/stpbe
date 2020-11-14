package com.zhuanjingkj.stpbe.facade.conf;

import com.zhuanjingkj.stpbe.facade.filter.HttpHeaderUserIdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpHeaderUserIdFilterConfig {
    @Bean
    public HttpHeaderUserIdFilter httpHeaderUserIdFilter() {
        return new HttpHeaderUserIdFilter();
    }
}
