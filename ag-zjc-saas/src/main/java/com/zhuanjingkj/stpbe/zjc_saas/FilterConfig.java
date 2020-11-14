package com.zhuanjingkj.stpbe.zjc_saas;

import com.zhuanjingkj.stpbe.zjc_saas.filter.IpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public IpFilter ipFilter() {
        return new IpFilter();
    }
}
