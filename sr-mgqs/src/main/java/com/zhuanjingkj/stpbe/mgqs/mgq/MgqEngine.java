package com.zhuanjingkj.stpbe.mgqs.mgq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MgqEngine {
    private final static Logger logger = LoggerFactory.getLogger(MgqEngine.class);

    public void demo() {
        logger.info("Milvus Graph Query Engine Demo");
        //ConnectParam connectParam = new ConnectParam.Builder().withHost("127.0.0.1").withPort(19530).build();
        //MilvusClient client = new MilvusGrpcClient(connectParam);
        //logger.info("Milvus client: " + client + "!");
    }
}
