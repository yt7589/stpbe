package com.zhuanjingkj.stpbe.mgqs.mgq;

import io.milvus.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class MgqEngine {
    public final static int REID_DIM = 512;
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
        final int dimension = REID_DIM; // ReID特征向量维数
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
        logger.info("创建Collection成功！！！！！！！！！！！！！！！1");
        // 生成partition：目前共分为6个，分别为：
        // head_truck, head_bus, head_car, tail_truck, tail_bus, tail_car
        final String partitionTag = "head_car";
        client.createPartition(collectionName, partitionTag);
        // Check the existence of partition
        if (!client.hasPartition(collectionName, partitionTag)) {
            logger.info("创建分区失败？？？？？？？？？？？？");
            throw new AssertionError("Partition not found!");
        }
        logger.info("创建分区成功！！！！！！！！！！！！！");
        // 插入记录
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        List<String> cllxfl = Arrays.asList("01", "02", "03");
        List<String> cllxzfl = Arrays.asList("01_01", "02_01", "03_01");
        List<String> csys = Arrays.asList("c01", "c02", "c03");
        List<String> clpp = Arrays.asList("b01", "b02", "b03");
        List<String> ppcx = Arrays.asList("x01", "x02", "x03");
        List<String> cxnk = Arrays.asList("n01", "n02", "n03");
        List<String> ppxhms = Arrays.asList("奥迪-A6L-2008", "奔驰-E级-2019", "宝马-3系-2018");
        List<List<Float>> embeddings = randomFloatVectors(3, dimension);
        InsertParam insertParam =
                InsertParam.create(collectionName)
                        .addField("CLLXFL", DataType.STRING, cllxfl)
                        .addField("CLLXZFL", DataType.STRING, cllxzfl)
                        .addField("CSYS", DataType.STRING, csys)
                        .addField("CLPP", DataType.STRING, clpp)
                        .addField("PPCX", DataType.STRING, ppcx)
                        .addField("CXNK", DataType.STRING, cxnk)
                        .addField("PPXHMS", DataType.STRING, ppxhms)
                        .addVectorField("embedding", DataType.VECTOR_FLOAT, embeddings)
                        .setEntityIds(ids)
                        .setPartitionTag(partitionTag);
        System.out.println("\n--------Insert Entities--------");
        List<Long> entityIds = client.insert(insertParam);
        System.out.println(entityIds);
        logger.info("插入记录成功！！！！！！！！！！！！！！！！！！！！！！！！");
    }

    private static List<List<Float>> randomFloatVectors(int vectorCount, int dimension) {
        SplittableRandom splitCollectionRandom = new SplittableRandom();
        List<List<Float>> vectors = new ArrayList<>(vectorCount);
        for (int i = 0; i < vectorCount; ++i) {
            splitCollectionRandom = splitCollectionRandom.split();
            DoubleStream doubleStream = splitCollectionRandom.doubles(dimension);
            List<Float> vector =
                    doubleStream.boxed().map(Double::floatValue).collect(Collectors.toList());
            vectors.add(vector);
        }
        return vectors;
    }
}
