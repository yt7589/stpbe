package com.zhuanjingkj.stpbe.mgqs;

import com.zhuanjingkj.stpbe.mgqs.mgq.MgqEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.stpbe.*"})
public class SrMgqsApplication {
    public static void main(String[] args) {
        System.out.println("Mulvus Graph Query System v0.0.1");
        //SpringApplication.run(SrMgqsApplication.class, args);
        MgqEngine engine = new MgqEngine();
        engine.demo();
    }
}
