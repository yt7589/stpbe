package com.zhuanjingkj.stpbe.idr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.stpbe.*"})
@MapperScan({"com.zhuanjingkj.stpbe.idr.mapper", "com.zhuanjingkj.stpbe.common.mapper"})
public class SrIdrApplication {

    public static void main(String[] args) {
        System.out.println("Traffic Video Image Structure Server v0.0.1");
        SpringApplication.run(SrIdrApplication.class, args);
    }
}
