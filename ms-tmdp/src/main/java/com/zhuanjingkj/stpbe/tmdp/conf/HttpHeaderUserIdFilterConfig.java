package com.zhuanjingkj.stpbe.tmdp.conf;

import com.zhuanjingkj.stpbe.tmdp.filter.HttpHeaderUserIdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpHeaderUserIdFilterConfig {
    @Bean
    public HttpHeaderUserIdFilter httpHeaderUserIdFilter() {
        return new HttpHeaderUserIdFilter();
    }
}
