package com.zhuanjingkj.stpbe.user;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class MsUserApplication {
    public static ConfigurableApplicationContext appCtx = null;
    public static void main(String[] args) {
        MsUserApplication.appCtx = SpringApplication.run(MsUserApplication.class, args);
        AppRegistry.putParam(AppConst.APP_CTX, MsUserApplication.appCtx);
    }
}
