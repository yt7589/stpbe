package com.zhuanjingkj.stpbe.user.conf;

import com.zhuanjingkj.stpbe.db.DataSourceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcTemplateConfig {
    @Bean
    public DataSourceRegistry dataSourceRegistry() {
        return DataSourceRegistry.getInstance();
    }
}
