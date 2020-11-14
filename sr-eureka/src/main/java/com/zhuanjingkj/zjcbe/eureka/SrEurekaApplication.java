package com.zhuanjingkj.zjcbe.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SrEurekaApplication {
    public static void main(String[] args) {
        System.out.println("Eureka Server v0.0.1");
        SpringApplication.run(SrEurekaApplication.class, args);
    }
}
