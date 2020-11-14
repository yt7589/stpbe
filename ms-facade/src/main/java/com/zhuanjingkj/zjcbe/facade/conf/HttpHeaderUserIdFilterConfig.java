package com.zhuanjingkj.zjcbe.facade.conf;

import com.zhuanjingkj.zjcbe.facade.filter.HttpHeaderUserIdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpHeaderUserIdFilterConfig {
    @Bean
    public HttpHeaderUserIdFilter httpHeaderUserIdFilter() {
        return new HttpHeaderUserIdFilter();
    }
}
