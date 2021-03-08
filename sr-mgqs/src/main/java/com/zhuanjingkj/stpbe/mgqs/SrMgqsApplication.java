package com.zhuanjingkj.stpbe.mgqs;

import com.zhuanjingkj.stpbe.common.mgq.MgqEngine;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

/**
 * 车辆品牌基于图搜系统的自动聚类工程
 * 将所有图片ReID保存到Milvus中。新来的车辆图片，求出车辆特征，从库中找到最相似的车辆，
 * 以该车辆作为本车辆的品牌车型年款。
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zhuanjingkj.stpbe.*"})
@MapperScan({"com.zhuanjingkj.stpbe.mgqs.mapper", "com.zhuanjingkj.stpbe.common.mapper"})
public class SrMgqsApplication {
    public final static Logger logger = LoggerFactory.getLogger(SrMgqsApplication.class);
    @Value("${mgqs.milvus.server.addr}")
    private String milvusServerAddr;
    @Value("${mgqs.milvus.server.port}")
    private String milvusServerPortStr;
    @Value("${mgqs.collection.name}")
    private String collectionName;
    @Value("${mgqs.reid.dim}")
    private String reidDimStr;
    @Value("${mgqs.tag.name}")
    private String tagName;

    public static void main(String[] args) {
        SpringApplication.run(SrMgqsApplication.class, args);
    }

    @PostConstruct
    public void startup() {
        System.out.println("车辆品牌基于图搜系统的自动聚类工程 v0.0.1");
        short milvusServerPort = Short.parseShort(milvusServerPortStr);
        int reidDim = Integer.parseInt(reidDimStr);
        System.out.println("milvusServerAddr=" + milvusServerAddr + "; milvusServerPort=" + milvusServerPort + "!");
        System.out.println("cn=" + collectionName + "; rd=" + reidDim + "; tag=" + tagName + "!");
        MgqEngine.initMgqsMilvus(milvusServerAddr, milvusServerPort,
        collectionName, reidDim, tagName); // 危险调用，请保持注释掉状态！！！！！！！！！！！！！！！！！！！！！！！！！
        MgqEngine.initialize();
        System.out.println("^_^ The End ^_^");
    }
}
