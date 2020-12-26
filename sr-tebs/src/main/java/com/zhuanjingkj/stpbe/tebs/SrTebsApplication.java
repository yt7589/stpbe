package com.zhuanjingkj.stpbe.tebs;

import com.zhuanjingkj.stpbe.common.mgq.GrqEngine;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Kafaka Reference: https://docs.spring.io/spring-kafka/docs/current/reference/html/
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.stpbe.*"})
@EnableDiscoveryClient
@MapperScan("com.zhuanjingkj.stpbe.tebs.mapper")
public class SrTebsApplication {
    public static void main(String[] args) {
        System.out.println("Traffic Event Bus System v0.0.1");
        //SpringApplication.run(SrTebsApplication.class, args);
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // 将删除所有数据！！！！！！！！！！！！！！！！！！！！！！！！！！！
        GrqEngine.createGrqDb(); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        GrqEngine.initializeGrp();
    }
}
