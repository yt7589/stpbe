package com.zhuanjingkj.stpbe.common.mgq;

import com.google.gson.JsonObject;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.data.vo.*;
import io.milvus.client.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrqEngine {
    private final static Logger logger = LoggerFactory.getLogger(GrqEngine.class);
    private static MilvusClient client = null;
    private static List<String> busCllxfl = null;
    private static List<String> carCllxfl = null;
    private static List<String> truckCllxfl = null;
    private static List<String> carCllxzfl = null;



    public static String getPartitionTag(String psfx, String cllxflCode, String cllxzflCode) {
        StringBuilder partitionTag = new StringBuilder();
        if (psfx.equals("1")) {
            partitionTag.append("head_");
        } else {
            partitionTag.append("tail_");
        }
        if (carCllxzfl.contains(cllxzflCode)) {
            partitionTag.append("car");
            return partitionTag.toString();
        }
        if (busCllxfl.contains(cllxflCode)) {
            partitionTag.append("bus");
        } else if (truckCllxfl.contains(cllxflCode)) {
            partitionTag.append("truck");
        }
        return partitionTag.toString();
    }

    private final static String GRQ_COLLECTION_NAME = "GRQ_COLLECTION_NAME";
    private final static String GRQ_ID = "GRQ_ID";
    private final static String GRQ_PN_HEAD_BUS = "GRQ_PN_HEAD_BUS";
    private final static String GRQ_PN_HEAD_CAR = "GRQ_PN_HEAD_CAR";
    private final static String GRQ_PN_HEAD_TRUCK = "GRQ_PN_HEAD_TRUCK";
    private final static String GRQ_PN_TAIL_BUS = "GRQ_PN_TAIL_BUS";
    private final static String GRQ_PN_TAIL_CAR = "GRQ_PN_TAIL_CAR";
    private final static String GRQ_PN_TAIL_TRUCK = "GRQ_PN_TAIL_TRUCK";

    private final static String MILVUS_INDEX = "nlist";
    private final static String REID_DIM = "REID_DIM";

    /**
     * 从Redis中取出向量编号
     * @return
     */
    public static long getGrqId(RedisTemplate<String, Serializable> redisTemplate) {
        return redisTemplate.opsForValue().increment(PropUtil.getValue(GRQ_ID));
    }

    public static long insertRecord(RedisTemplate<String, Serializable> redisTemplate,
                                    String partitionTag,
                                    VehicleVO vo) {
        String collectionName = PropUtil.getValue(GRQ_COLLECTION_NAME);
        long grqId = getGrqId(redisTemplate);
        logger.info("##### grqId=" + grqId + "!");
        // 插入记录
        List<Long> ids = new ArrayList<>(Arrays.asList(grqId));
        VehicleWztzVO vehicleWztzVo = vo.getVehicleWztzVo();
        VehicleCxtzVO vehicleCxtzVo = vo.getVehicleCxtzVo();
        VehicleCltzxlVO vehicleCltzxlVo = vo.getVehicleCltzxlVo();
        List<Long> tvisJsonIds = Arrays.asList(vo.getTvisJsonId());
        List<Long> vehsIdxs = Arrays.asList(vo.getVehsIdx());
        List<List<Float>> embeddings = Arrays.asList(vehicleCltzxlVo.getCltzxl());
        InsertParam insertParam =
                new InsertParam.Builder(collectionName).withFloatVectors(embeddings).build();
        logger.info("##### before add to milvus");
        InsertResponse insertResponse = client.insert(insertParam);
        logger.info("##### before add to milvus");
        // Insert returns a list of vector ids that you will be using (if you did not supply them
        // yourself) to reference the vectors you just inserted
        List<Long> entityIds = insertResponse.getVectorIds();
        logger.info("##### get entityIds");
        JSONObject jo = new JSONObject();
        long entityId = 0;
        if (entityIds != null && !entityIds.isEmpty() && entityIds.size() > 0) {
            entityId = entityIds.get(0);
            logger.info("##### entityId=" + entityId + "!");
            jo.put("entityId", entityId);
            jo.put("tvisJsonId", tvisJsonIds.get(0));
            jo.put("vehsIdx", vehsIdxs.get(0));
            redisTemplate.opsForValue().set("" + entityId, jo.toString());
            logger.info("##### add jo to redis." + entityId + "!");
        }
        // Flush data in collection
        Response flushResponse = client.flush(collectionName);
        return entityId;
    }

    public static List<TvisGrqRstVO> findTopK(RedisTemplate<String, Serializable> redisTemplate, String partitionTag, List<List<Float>> queryEmbedding, long topK) {
        String collectionName = PropUtil.getValue(GRQ_COLLECTION_NAME);
        List<TvisGrqRstVO> rst = new ArrayList<>();
        TvisGrqRstVO vo = null;
        // Search vectors
        // Searching the first 5 vectors of the vectors we just inserted
        final int searchBatchSize = 1;
        List<List<Float>> vectorsToSearch = queryEmbedding.subList(0, searchBatchSize);
        // Based on the index you created, the available search parameters will be different. Refer to
        // the Milvus documentation for how to set the optimal parameters based on your needs.
        JsonObject searchParamsJson = new JsonObject();
        searchParamsJson.addProperty("nprobe", 20);
        SearchParam searchParam =
                new SearchParam.Builder(collectionName)
                        .withFloatVectors(vectorsToSearch)
                        .withTopK(topK)
                        .withParamsInJson(searchParamsJson.toString())
                        .build();
        logger.info("##### befor milvus search...");
        SearchResponse searchResponse = client.search(searchParam);
        logger.info("##### after milvus search");
        if (searchResponse.ok()) {
            List<List<SearchResponse.QueryResult>> queryResultsList =
                    searchResponse.getQueryResultsList();
            for (int i = 0; i < queryResultsList.size(); i++) {
                // Since we are searching for vector that is already present in the collection,
                // the first result vector should be itself and the distance (inner product) should be
                // very close to 1 (some precision is lost during the process)
                SearchResponse.QueryResult firstQueryResult = queryResultsList.get(i).get(0);
                long vectorId = firstQueryResult.getVectorId();
                logger.info("##### vectorId=" + vectorId + "!");
                vo = new TvisGrqRstVO();
                JSONObject jo = new JSONObject(redisTemplate.opsForValue().get("" + vectorId).toString());
                vo.setTvisJsonId(jo.getLong("tvisJsonId"));
                vo.setVehsIdx(jo.getInt("vehsIdx"));
                vo.setDist(firstQueryResult.getDistance());
                rst.add(vo);
            }
        }
        // You can also get result ids and distances separately
        //List<List<Long>> resultIds = searchResponse.getResultIdsList();
        //List<List<Float>> resultDistances = searchResponse.getResultDistancesList();
        return rst;
    }

    /**
     * 初始化GRP系统，每次系统启动时调用
     */
    public static void initializeGrp() {
//        if (null == client) {
//            String appMilvusHost = PropUtil.getValue(AppConst.APP_MILVUS_HOST);
//            short appMilvusPort = Short.parseShort(PropUtil.getValue(AppConst.APP_MILVUS_PORT));
//            ConnectParam connectParam = new ConnectParam.Builder().withHost(appMilvusHost).withPort(appMilvusPort).build();
//            client = new MilvusGrpcClient(connectParam);
//        }
//        if (null == carCllxzfl || null == carCllxfl || null == busCllxfl || null == truckCllxfl) {
//            System.out.println("MgqEngine.initialize 2.1");
//            // 客车初始化
//            busCllxfl = new ArrayList<>();
//            busCllxfl.add("11");
//            busCllxfl.add("12");
//            busCllxfl.add("13"); // 仅包括134
//            busCllxfl.add("14");
//            // 轿车初始化
//            carCllxzfl = new ArrayList<>();
//            carCllxzfl.add("131");
//            carCllxzfl.add("132");
//            carCllxzfl.add("133");
//            // 货车初始化
//            truckCllxfl = new ArrayList<>();
//            truckCllxfl.add("21");
//            truckCllxfl.add("22");
//            System.out.println("MgqEngine.initialize 3");
//        }
    }

    /**
     * 创建图像ReID搜索库
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * ！！！！！！！！！！ 仅需要调用一次，会清空以前库中内容  ！！！！！！！！！！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     */
    public static void createGrqDb() {
//        String appMilvusHost = PropUtil.getValue(AppConst.APP_MILVUS_HOST);
//        short appMilvusPort = Short.parseShort(PropUtil.getValue(AppConst.APP_MILVUS_PORT));
//        ConnectParam connectParam = new ConnectParam.Builder().
//                withHost(appMilvusHost).
//                withPort(appMilvusPort).build();
//        client = new MilvusGrpcClient(connectParam);// 创建Collection
//        final String collectionName = PropUtil.getValue(GRQ_COLLECTION_NAME);
//        createCollection(collectionName);
//        createPartition(collectionName, PropUtil.getValue(GRQ_PN_HEAD_BUS));
//        createPartition(collectionName, PropUtil.getValue(GRQ_PN_HEAD_CAR));
//        createPartition(collectionName, PropUtil.getValue(GRQ_PN_HEAD_TRUCK));
//        createPartition(collectionName, PropUtil.getValue(GRQ_PN_TAIL_BUS));
//        createPartition(collectionName, PropUtil.getValue(GRQ_PN_TAIL_CAR));
//        createPartition(collectionName, PropUtil.getValue(GRQ_PN_TAIL_TRUCK));
//        // Create index for the collection
//        // We choose IVF_SQ8 as our index type here. Refer to IndexType javadoc for a
//        // complete explanation of different index types
//        // 高速查询，具有尽可能高的召回率
//        final IndexType indexType = IndexType.IVFLAT;   //IndexType.IVF_SQ8; // 资源有限时使用
//        // Each index type has its optional parameters you can set. Refer to the Milvus documentation
//        // for how to set the optimal parameters based on your needs.
//        JsonObject indexParamsJson = new JsonObject();
//        indexParamsJson.addProperty(MILVUS_INDEX, 16384);
//        Index index =
//                new Index.Builder(collectionName, indexType)
//                        .withParamsInJson(indexParamsJson.toString())
//                        .build();
//        Response createIndexResponse = client.createIndex(index);
//        if (!createIndexResponse.ok()) {
//            throw new AssertionError("创建索引失败：" + createIndexResponse.getMessage() + "!");
//        }
    }

    public static void createCollection(String collectionName) {
        HasCollectionResponse hcResp = client.hasCollection(collectionName);
        if (hcResp.ok() && hcResp.hasCollection()) {
            client.dropCollection(collectionName);
        }
        logger.info("删除已有Collection");
        // 添加索引
        final int dimension = Integer.parseInt(PropUtil.getValue(REID_DIM)); // ReID特征向量维数
        final int indexFileSize = 1024; // maximum size (in MB) of each index file
        final MetricType metricType = MetricType.IP; // we choose IP (Inner Product) as our metric type
        CollectionMapping collectionMapping =
                new CollectionMapping.Builder(collectionName, dimension)
                        .withIndexFileSize(indexFileSize)
                        .withMetricType(metricType)
                        .build();
        Response createCollectionResponse = client.createCollection(collectionMapping);
        if (!createCollectionResponse.ok()) {
            throw new AssertionError("创建Collection失败：Collection not found!");
        }
    }

    public static void createPartition(String collectionName, String partitionTag) {
        Response resp = client.createPartition(collectionName, partitionTag);
        if (!resp.ok()) {
            throw new AssertionError("创建" + partitionTag + "分区失败：Partition not found!");
        }
    }
}
