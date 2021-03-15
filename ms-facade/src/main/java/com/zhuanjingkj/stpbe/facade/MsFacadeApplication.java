package com.zhuanjingkj.stpbe.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zhuanjingkj.stpbe.facade.fcc")
public class MsFacadeApplication {
    public static void main(String[] args) {
        System.out.println("Eureka Server v0.0.1");
        SpringApplication.run(MsFacadeApplication.class, args);
    }
}
