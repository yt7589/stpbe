package com.zhuanjingkj.stpbe.tvis_server.wxs2102;

import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.stpbe.*"})
@MapperScan({"com.zhuanjingkj.stpbe.tvis_server.wxs2102.mapper", "com.zhuanjingkj.stpbe.common.mapper"})
public class SrTsWxs2102Application {

    public static void main(String[] args) {
        System.out.println("Traffic Video Image Structure Server v0.0.1");
        TvisUtil.TVIS_RST_TIMEOUT = 600;
        SpringApplication.run(SrTsWxs2102Application.class, args);
    }
}
