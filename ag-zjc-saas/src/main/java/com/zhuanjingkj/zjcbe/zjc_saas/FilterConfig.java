package com.zhuanjingkj.zjcbe.zjc_saas;

import com.zhuanjingkj.zjcbe.zjc_saas.filter.IpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public IpFilter ipFilter() {
        return new IpFilter();
    }
}
