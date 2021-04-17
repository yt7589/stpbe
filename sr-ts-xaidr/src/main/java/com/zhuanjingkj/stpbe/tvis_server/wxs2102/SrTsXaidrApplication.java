package com.zhuanjingkj.stpbe.tvis_server.wxs2102;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.stpbe.tvis_server.wxs2102.*"})
//@MapperScan({"com.zhuanjingkj.stpbe.tvis_server.xaidr.mapper", "com.zhuanjingkj.stpbe.common.mapper"})
public class SrTsXaidrApplication {

    public static void main(String[] args) {
        System.out.println("Traffic Video Image Structure Server v0.0.1");
        SpringApplication.run(SrTsXaidrApplication.class, args);
    }
}
