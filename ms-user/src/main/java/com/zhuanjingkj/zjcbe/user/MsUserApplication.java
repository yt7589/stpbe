package com.zhuanjingkj.zjcbe.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsUserApplication {
    public static void main(String[] args) {
        System.out.println("Eureka Server v0.0.1");
        SpringApplication.run(MsUserApplication.class, args);
    }
}
