package com.zhuanjingkj.stpbe.tvis_server;

import com.mysql.cj.util.StringUtils;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.mgq.GrqEngine;
import com.zhuanjingkj.stpbe.tvis_server.task.TasScheduledTask;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private TvisJsonMapper tvisJsonMapper;
    @Value("${app.run-mode}")
    private String appRunMode;

    public static void main(String[] args) {
        System.out.println("Traffic Video Image Structure Server v0.0.1");
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // 将删除所有数据！！！！！！！！！！！！！！！！！！！！！！！！！！！
        GrqEngine.createGrqDb(); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        GrqEngine.initializeGrp();
        SpringApplication.run(SrTvisServerApplication.class, args);
    }

    @PostConstruct
    public void startScheduledTask() {
        System.out.println("postConstruct 1");
        if (StringUtils.isNullOrEmpty(AppRegistry.tvisJsonTblName)) {
            AppRegistry.tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
        }
        System.out.println("postConstruct 2");
        System.out.println("appRunMode=" + appRunMode + "!");
        if (appRunMode.equals("1")) {
            System.out.println("postConstruct 3");
            Thread thd = new Thread(tasScheduledTask);
            thd.start();
        }
    }
}
