package com.zhuanjingkj.stpbe.tebs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.stpbe.*"})
public class SrTebsApplication {
    public static void main(String[] args) {
        System.out.println("Traffic Event Bus System v0.0.1");
        SpringApplication.run(SrTebsApplication.class, args);
    }
}
