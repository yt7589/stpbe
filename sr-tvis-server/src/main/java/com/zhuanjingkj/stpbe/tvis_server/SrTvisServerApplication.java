package com.zhuanjingkj.stpbe.tvis_server;

import com.zhuanjingkj.stpbe.common.mgq.GrqEngine;
import com.zhuanjingkj.stpbe.tvis_server.task.TasScheduledTask;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
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
@MapperScan({"com.zhuanjingkj.stpbe.tvis_server.mapper", "com.zhuanjingkj.stpbe.common.mapper"})
public class SrTvisServerApplication {
    @Autowired
    private TasScheduledTask tasScheduledTask;

    public static void main(String[] args) {
        System.out.println("Traffic Video Image Structure Server v0.0.1");
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // 将删除所有数据！！！！！！！！！！！！！！！！！！！！！！！！！！！
        //GrqEngine.createGrqDb(); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        GrqEngine.initializeGrp();
        SpringApplication.run(SrTvisServerApplication.class, args);
    }

    @PostConstruct
    public void startScheduledTask() {
        Thread thd = new Thread(tasScheduledTask);
        thd.start();
    }
}
