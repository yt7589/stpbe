package com.zhuanjingkj.stpbe.mgqs;

//import com.zhuanjingkj.stpbe.mgqs.mgq.MgqEngine;
import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;
import com.zhuanjingkj.stpbe.mgqs.mgq.MgqEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.*;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.stpbe.*"})
public class SrMgqsApplication {
    @Autowired
    private MgqEngine mgqEngine;

    public static void main(String[] args) {
        System.out.println("Mulvus Graph Query System v0.0.3");
        SpringApplication.run(SrMgqsApplication.class, args);
        MgqEngine.initMilvus(); // 危险调用，请保持注释掉状态！！！！！！！！！！！！！！！！！！！！！！！！！
        System.out.println("创建Collection和Partition");
        MgqEngine.initialize();
        System.out.println("获取Milvus客户端");
    }
}
