package com.zhuanjingkj.stpbe.mgqs.mgq;

import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;
import io.milvus.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class MgqEngine {
    public final static String COLLECTION_NAME = "tvis";
    // Partition名称定义
    public final static String PN_HEAD_CAR = "head_car";
    public final static String PN_HEAD_BUS = "head_bus";
    public final static String PN_HEAD_TRUCK = "head_truck";
    public final static String PN_TAIL_CAR = "tail_car";
    public final static String PN_TAIL_BUS = "tail_bus";
    public final static String PN_TAIL_TRUCK = "tail_truck";
    // 车型特征字段定义
    public final static String FLD_CLLXFL = "CLLXFL";
    public final static String FLD_CLLXZFL = "CLLXZFL";
    public final static String FLD_CSYS = "CSYS";
    public final static String FLD_CLPP = "CLPP";
    public final static String FLD_PPCX = "PPCX";
    public final static String FLD_CXNK = "CXNK";
    public final static String FLD_PPXHMS = "PPXHMS";
    // ReID特征向量维度定义
    public final static int REID_DIM = 512;
    private final static Logger logger = LoggerFactory.getLogger(MgqEngine.class);
    private static MilvusClient client = null;

    /**
     * 在application类中调用进行初始化，供所有程序使用
     */
    public static void initialize() {
        if (null == client) {
            ConnectParam connectParam = new ConnectParam.Builder().withHost("192.168.2.15").withPort(19530).build();
            client = new MilvusGrpcClient(connectParam);
        }
    }

    /**
     * 从Redis中取出向量编号
     * @return
     */
    public static long getTzxlId() {
        return 1L;
    }

    public static long insertRecord(String partitionTag, List<VehicleCxtzVo> vos, List<List<Float>> embeddings) {
        long tzxlId = getTzxlId();
        // 插入记录
        List<Long> ids = new ArrayList<>(Arrays.asList(tzxlId));
        VehicleCxtzVo vo = vos.get(0);
        List<Integer> cllxfl = Arrays.asList(vo.getCllxfl());
        List<Integer> cllxzfl = Arrays.asList(vo.getCllxzfl());
        List<Integer> csys = Arrays.asList(vo.getCsys());
        List<Integer> clpp = Arrays.asList(vo.getClpp());
        List<Integer> ppcx = Arrays.asList(vo.getPpcx());
        List<Integer> cxnk = Arrays.asList(vo.getCxnk());
        List<Integer> ppxhms = Arrays.asList(vo.getPpxhms());
        //List<List<Float>> embeddings = randomFloatVectors(3, REID_DIM);
        InsertParam insertParam =
                InsertParam.create(COLLECTION_NAME)
                        .addField(FLD_CLLXFL, DataType.INT32, cllxfl)
                        .addField(FLD_CLLXZFL, DataType.INT32, cllxzfl)
                        .addField(FLD_CSYS, DataType.INT32, csys)
                        .addField(FLD_CLPP, DataType.INT32, clpp)
                        .addField(FLD_PPCX, DataType.INT32, ppcx)
                        .addField(FLD_CXNK, DataType.INT32, cxnk)
                        .addField(FLD_PPXHMS, DataType.INT32, ppxhms)
                        .addVectorField("embedding", DataType.VECTOR_FLOAT, embeddings)
                        .setEntityIds(ids)
                        .setPartitionTag(partitionTag);
        List<Long> entityIds = client.insert(insertParam);
        long entityId = 0;
        if (entityIds != null && !entityIds.isEmpty() && entityIds.size() > 0) {
            entityId = entityIds.get(0);
        }
        client.flush(COLLECTION_NAME);
        return entityId;
    }

    public static VehicleCxtzVo findTopK(String partitionTag, List<List<Float>> queryEmbedding, long topK) {
        String dsl =
                String.format(
                        "{\"bool\": {"
                                + "\"must\": [{"
                                + "    \"vector\": {"
                                + "        \"embedding\": {"
                                + "            \"topk\": %d, \"metric_type\": \"L2\", " +
                                "\"type\": \"float\", \"query\": %s"
                                + "    }}}]}}",
                        topK, queryEmbedding.toString());

        // Only specified fields in `setParamsInJson` will be returned from search request.
        // If not set, all fields will be returned.
        SearchParam searchParam =
                SearchParam.create(COLLECTION_NAME)
                        .setDsl(dsl)
                        .setParamsInJson("{\"fields\": [\"" + FLD_CLLXFL + "\", \"" +
                                FLD_CLLXZFL + "\", " +
                                "\"" + FLD_CSYS + "\", \"" + FLD_CLPP + "\", " +
                                "\"" + FLD_PPCX + "\", \"" + FLD_CXNK + "\", " +
                                "\"" + FLD_PPXHMS + "\", \"embedding\"]}");
        System.out.println("\n--------Search Result--------");
        SearchResult searchResult = client.search(searchParam);
        System.out.println("- ids: " + searchResult.getResultIdsList().toString());
        System.out.println("- distances: " + searchResult.getResultDistancesList().toString());
        int idx = 0;
        float top1Dist = searchResult.getResultDistancesList().get(0).get(idx);
        Map<String, Object> rec = searchResult.getFieldsMap().get(0).get(idx);
        System.out.println("第一条：" + rec.get("CLPP") + "; dist=" + top1Dist + "!");
        VehicleCxtzVo vo = new VehicleCxtzVo();
        vo.setCllxfl((Integer)rec.get("cllxfl"));
        return vo;
    }

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
        long beforeEntityCount = client.countEntities(collectionName);
        client.flush(collectionName);
        long afterEntityCount = client.countEntities(collectionName);
        System.out.println("\n--------Flush Collection--------");
        System.out.printf("There are %d films in the collection before flush.\n", beforeEntityCount);
        System.out.printf("There are %d films in the collection after flush.\n", afterEntityCount);
        // 图搜示例
        List<List<Float>> queryEmbedding = new ArrayList<>(); //randomFloatVectors(1, dimension);
        queryEmbedding.add(embeddings.get(0));
        final long topK = 3;
        String dsl =
                String.format(
                        "{\"bool\": {"
                                + "\"must\": [{"
                                + "    \"vector\": {"
                                + "        \"embedding\": {"
                                + "            \"topk\": %d, \"metric_type\": \"L2\", " +
                                "\"type\": \"float\", \"query\": %s"
                                + "    }}}]}}",
                        topK, queryEmbedding.toString());

        // Only specified fields in `setParamsInJson` will be returned from search request.
        // If not set, all fields will be returned.
        SearchParam searchParam =
                SearchParam.create(collectionName)
                        .setDsl(dsl)
                        .setParamsInJson("{\"fields\": [\"CLLXFL\", \"CLLXZFL\", " +
                                "\"CSYS\", \"CLPP\", \"PPCX\", \"CXNK\", " +
                                "\"PPXHMS\", \"embedding\"]}");
        System.out.println("\n--------Search Result--------");
        SearchResult searchResult = client.search(searchParam);
        System.out.println("- ids: " + searchResult.getResultIdsList().toString());
        System.out.println("- distances: " + searchResult.getResultDistancesList().toString());
        int idx = 0;
        float top1Dist = searchResult.getResultDistancesList().get(0).get(idx);
        Map<String, Object> rec = searchResult.getFieldsMap().get(0).get(idx);
        System.out.println("第一条：" + rec.get("CLPP") + "; dist=" + top1Dist + "!");
        for (List<Map<String, Object>> singleQueryResult : searchResult.getFieldsMap()) {
            // We only have 1 film returned
            for (Map<String, Object> res : singleQueryResult) {
                System.out.println("### 车辆类型分类: " + res.get("CLLXFL"));
                System.out.println("### 车辆类型子分类: " + res.get("CLLXZFL"));
                System.out.println("### 车身颜色：" + res.get("CSYS"));
                System.out.println("### 车辆品牌：" + res.get("CLPP"));
                System.out.println("### 品牌车型：" + res.get("PPCX"));
                System.out.println("### 车型年款：" + res.get("CXNK"));
                System.out.println("### 品牌型号描述：" + res.get("PPXHMS"));
                // System.out.println("- embedding: " + res.get("embedding"));
            }
        }
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



    /**
     * 初始化Milvus系统，创建Collection和Partition，只需要调用一次，
     * 再次调用会删除所有数据！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     */
    public static void initMilvus() {
        ConnectParam connectParam = new ConnectParam.Builder().withHost("192.168.2.15").withPort(19530).build();
        client = new MilvusGrpcClient(connectParam);
        // 创建Collection
        final String collectionName = COLLECTION_NAME;
        if (client.listCollections().contains(collectionName)) {
            client.dropCollection(collectionName);
        }
        logger.info("删除已有Collection");
        final int dimension = REID_DIM; // ReID特征向量维数
        CollectionMapping collectionMapping =
                CollectionMapping.create(collectionName)
                        .addField(FLD_CLLXFL, DataType.INT32) // 车辆类型分类
                        .addField(FLD_CLLXZFL, DataType.INT32) // 车辆类型子分类
                        .addField(FLD_CSYS, DataType.INT32) // 车身颜色
                        .addField(FLD_CLPP, DataType.INT32) // 车辆品牌
                        .addField(FLD_PPCX, DataType.INT32) // 品牌车型
                        .addField(FLD_CXNK, DataType.INT32) // 车型年款
                        .addField(FLD_PPXHMS, DataType.INT32) // 品牌型号描述
                        .addVectorField("embedding", DataType.VECTOR_FLOAT, dimension)
                        .setParamsInJson("{\"segment_row_limit\": 4096, \"auto_id\": false}");
        client.createCollection(collectionMapping);
        // Check the existence of collection
        if (!client.hasCollection(collectionName)) {
            throw new AssertionError("创建Collection失败：Collection not found!");
        }
        // 生成partition：目前共分为6个，分别为：
        // head_truck, head_bus, head_car, tail_truck, tail_bus, tail_car
        createPartitionMilvus(COLLECTION_NAME, PN_HEAD_CAR);
        createPartitionMilvus(COLLECTION_NAME, PN_HEAD_BUS);
        createPartitionMilvus(COLLECTION_NAME, PN_HEAD_TRUCK);
        createPartitionMilvus(COLLECTION_NAME, PN_TAIL_CAR);
        createPartitionMilvus(COLLECTION_NAME, PN_TAIL_BUS);
        createPartitionMilvus(COLLECTION_NAME, PN_TAIL_TRUCK);
        logger.info("创建分区成功！");
    }

    private static void createPartitionMilvus(String collectionName, String partitionTag) {
        client.createPartition(collectionName, partitionTag);
        if (!client.hasPartition(collectionName, partitionTag)) {
            throw new AssertionError("创建" + partitionTag + "分区失败：Partition not found!");
        }
    }
}
