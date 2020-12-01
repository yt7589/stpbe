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
                        .addField("CLLXFL", DataType.INT32) // 车辆类型分类
                        .addField("CLLXZFL", DataType.INT32) // 车辆类型子分类
                        .addField("CSYS", DataType.INT32) // 车身颜色
                        .addField("CLPP", DataType.INT32) // 车辆品牌
                        .addField("PPCX", DataType.INT32) // 品牌车型
                        .addField("CXNK", DataType.INT32) // 车型年款
                        .addField("PPXHMS", DataType.INT32) // 品牌型号描述
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
        List<Integer> cllxfl = Arrays.asList(1, 2, 3);
        List<Integer> cllxzfl = Arrays.asList(1001, 1002, 1003);
        List<Integer> csys = Arrays.asList(2001, 2002, 2003);
        List<Integer> clpp = Arrays.asList(100001, 100002, 100003);
        List<Integer> ppcx = Arrays.asList(210001, 210002, 210003);
        List<Integer> cxnk = Arrays.asList(220001, 220002, 220003);
        List<Integer> ppxhms = Arrays.asList(300001, 300002, 300003);
        List<List<Float>> embeddings = randomFloatVectors(3, dimension);
        InsertParam insertParam =
                InsertParam.create(collectionName)
                        .addField("CLLXFL", DataType.INT32, cllxfl)
                        .addField("CLLXZFL", DataType.INT32, cllxzfl)
                        .addField("CSYS", DataType.INT32, csys)
                        .addField("CLPP", DataType.INT32, clpp)
                        .addField("PPCX", DataType.INT32, ppcx)
                        .addField("CXNK", DataType.INT32, cxnk)
                        .addField("PPXHMS", DataType.INT32, ppxhms)
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
