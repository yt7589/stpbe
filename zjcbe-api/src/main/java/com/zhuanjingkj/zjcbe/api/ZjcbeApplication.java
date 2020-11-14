package com.zhuanjingkj.zjcbe.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @description: 启动类
 * @author: liuxiaogang.bj
 * @create: 2019/9/25 11:15
 **/

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.zjcbe.*"})
@MapperScan(basePackages = {"com.zhuanjingkj.zjcbe.domain.mapper"})
public class ZjcbeApplication {
	@PostConstruct
	void setDefaultTimezone() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
	}

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		SpringApplication.run(ZjcbeApplication.class, args);
	}
}
