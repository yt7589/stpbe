package com.zhuanjingkj.zjcbe.applications;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/8/19 17:07
 **/

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.zjcbe.*"})
@MapperScan(basePackages = {"com.zhuanjingkj.zjcbe.domain.mapper"})
public class AppsApplication {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		SpringApplication.run(AppsApplication.class, args);
	}

}
