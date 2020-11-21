package com.zhuanjingkj.stpbe.dtmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.stpbe.*"})
public class SrDtmpApplication {
    public static void main(String[] args) {
        System.out.println("Deamon Task Management Platform v0.0.1");
        SpringApplication.run(SrDtmpApplication.class, args);
    }
}
