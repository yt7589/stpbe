package com.zhuanjingkj.stpbe.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SrEurekaApplication {
    public static void main(String[] args) {
        System.out.println("Eureka Server v0.0.1");
        SpringApplication.run(SrEurekaApplication.class, args);
    }
}
