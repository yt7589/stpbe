package com.zhuanjingkj.zjcbe.zjc_saas;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 设置连接超时间为10秒，读取超时时间为60秒
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(10*1000, 60*1000);
    }
}
