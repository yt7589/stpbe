package com.zhuanjingkj.stpbe.mgqs.mgq;

import io.milvus.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MgqEngine {
    private final static Logger logger = LoggerFactory.getLogger(MgqEngine.class);

    public static void demo() {
        logger.info("Milvus Graph Query Engine Demo");
        ConnectParam connectParam = new ConnectParam.Builder().withHost("192.168.2.15").withPort(19530).build();
        MilvusClient client = new MilvusGrpcClient(connectParam);
        // 创建Collection
        final String collectionName = "tvis01";
        if (client.listCollections().contains(collectionName)) {
            client.dropCollection(collectionName);
        }
        logger.info("删除已有Collection");
        final int dimension = 512; // ReID特征向量维数
        CollectionMapping collectionMapping =
                CollectionMapping.create(collectionName)
                        .addField("CLLXFL", DataType.STRING) // 车辆类型分类
                        .addField("CLLXZFL", DataType.STRING) // 车辆类型子分类
                        .addField("CSYS", DataType.STRING) // 车身颜色
                        .addField("CLPP", DataType.STRING) // 车辆品牌
                        .addField("PPCX", DataType.STRING) // 品牌车型
                        .addField("CXNK", DataType.STRING) // 车型年款
                        .addField("PPXHMS", DataType.STRING) // 品牌型号描述
                        .addVectorField("embedding", DataType.VECTOR_FLOAT, dimension)
                        .setParamsInJson("{\"segment_row_limit\": 4096, \"auto_id\": false}");
        logger.info("创创CollectionMapping");
        client.createCollection(collectionMapping);
        logger.info("创建Collection");
        // Check the existence of collection
        if (!client.hasCollection(collectionName)) {
            logger.info("创建失败：？？？？？？？？");
            throw new AssertionError("Collection not found!");
        }
        logger.info("创建成功！！！！！！！！！！！！！！！1");
    }
}
