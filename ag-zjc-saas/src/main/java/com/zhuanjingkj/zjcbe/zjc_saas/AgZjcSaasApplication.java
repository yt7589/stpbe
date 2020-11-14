package com.zhuanjingkj.zjcbe.zjc_saas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients(basePackages = "com.zhuanjingkj.zjcbe.zjc_saas.fcc")
@EnableHystrix
@EnableZuulProxy
public class AgZjcSaasApplication {
    public static void main(String[] args) {
        System.out.println("Eureka Server v0.0.1");
        SpringApplication.run(AgZjcSaasApplication.class, args);
    }
}
