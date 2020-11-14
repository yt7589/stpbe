package com.zhuanjingkj.zjcbe.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:性能监控拦截器
 * @author: wangliying
 * @create: 2018-08-03
 **/
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("WebAppConfigurer.addInterceptors() Regist PerformanceHandlerInterceptor successful!");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/vehicle/api/**")
				.allowCredentials(true)
				.allowedHeaders("*")
				.allowedOrigins("*")
				.allowedMethods("*");
	}
}
