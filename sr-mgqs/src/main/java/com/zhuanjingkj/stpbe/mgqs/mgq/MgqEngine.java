package com.zhuanjingkj.stpbe.mgqs.mgq;

import io.milvus.client.ConnectParam;
import io.milvus.client.MilvusClient;
import io.milvus.client.MilvusGrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MgqEngine {
    private final static Logger logger = LoggerFactory.getLogger(MgqEngine.class);

    public static void demo() {
        logger.info("Milvus Graph Query Engine Demo");
        ConnectParam connectParam = new ConnectParam.Builder().withHost("192.168.2.15").withPort(19530).build();
        MilvusClient client = new MilvusGrpcClient(connectParam);
        logger.info("Milvus client: " + client + "!");
    }
}
