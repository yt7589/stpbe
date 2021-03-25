package com.zhuanjingkj.stpbe.common.mgq;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.data.vo.*;
import io.milvus.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    /**
     * 从Redis中取出向量编号
     * @return
     */
    public static long getGrqId(RedisTemplate<String, Serializable> redisTemplate) {
        return redisTemplate.opsForValue().increment(PropUtil.getValue("GRQ_ID"));
    }

    public static long insertRecord(RedisTemplate<String, Serializable> redisTemplate,
                                    String partitionTag,
                                    VehicleVo vo) {
//        long grqId = getGrqId(redisTemplate);
//        // 插入记录
//        List<Long> ids = new ArrayList<>(Arrays.asList(grqId));
//        VehicleWztzVo vehicleWztzVo = vo.getVehicleWztzVo();
//        VehicleCxtzVo vehicleCxtzVo = vo.getVehicleCxtzVo();
//        VehicleCltzxlVo vehicleCltzxlVo = vo.getVehicleCltzxlVo();
//        List<Long> tvisJsonIds = Arrays.asList(vo.getTvisJsonId());
//        List<Long> vehsIdxs = Arrays.asList(vo.getVehsIdx());
//        List<List<Float>> embeddings = Arrays.asList(vehicleCltzxlVo.getCltzxl());
//        InsertParam insertParam =
//                InsertParam.create(PropUtil.getValue("GRQ_COLLECTION_NAME"))
//                        .addField(PropUtil.getValue("GRQ_TVIS_JSON_ID"), DataType.INT64, tvisJsonIds)
//                        .addField(PropUtil.getValue("GRQ_VEHS_IDX"), DataType.INT64, vehsIdxs)
//                        .addVectorField("embedding", DataType.VECTOR_FLOAT, embeddings)
//                        .setEntityIds(ids)
//                        .setPartitionTag(partitionTag);
//        List<Long> entityIds = client.insert(insertParam);
        long entityId = 0;
//        if (entityIds != null && !entityIds.isEmpty() && entityIds.size() > 0) {
//            entityId = entityIds.get(0);
//        }
//        client.flush(PropUtil.getValue("GRQ_COLLECTION_NAME"));
        return entityId;
    }

    public static List<TvisGrqRstVo> findTopK(String partitionTag, List<List<Float>> queryEmbedding, long topK) {
//        String dsl =
//                String.format(
//                        "{\"bool\": {"
//                                + "\"must\": [{"
//                                + "    \"vector\": {"
//                                + "        \"embedding\": {"
//                                + "            \"topk\": %d, \"metric_type\": \"L2\", " +
//                                "\"type\": \"float\", \"query\": %s"
//                                + "    }}}]}}",
//                        topK, queryEmbedding.toString());
//        // Only specified fields in `setParamsInJson` will be returned from search request.
//        // If not set, all fields will be returned.
//        SearchParam searchParam =
//                SearchParam.create(PropUtil.getValue("GRQ_COLLECTION_NAME"))
//                        .setDsl(dsl)
//                        .setParamsInJson("{\"fields\": [\"" + PropUtil.getValue("GRQ_TVIS_JSON_ID") + "\", \"" +
//                                PropUtil.getValue("GRQ_VEHS_IDX") + "\", \"embedding\"]}");
//        SearchResult searchResult = client.search(searchParam);
//        int idx = 0;
        List<TvisGrqRstVo> rst = new ArrayList<>();
//        TvisGrqRstVo vo = null;
//        long grpId = 0;
//        double dist = 0.0;
//        if (searchResult.getResultIdsList().size() <= 0) {
//            return rst;
//        }
//        int baseIdx = 0;
//        int count = searchResult.getResultIdsList().get(baseIdx).size();
//        for (idx=0; idx<count; idx++) {
//            vo = new TvisGrqRstVo();
//            vo.setGrqId(searchResult.getResultIdsList().get(baseIdx).get(idx));
//            vo.setDist(searchResult.getResultDistancesList().get(baseIdx).get(idx));
//            Map<String, Object> rec = searchResult.getFieldsMap().get(baseIdx).get(idx);
//            vo.setTvisJsonId((Long) rec.get(PropUtil.getValue("GRQ_TVIS_JSON_ID")));
//            vo.setVehsIdx((long) rec.get(PropUtil.getValue("GRQ_VEHS_IDX")));
//            rst.add(vo);
//        }
        return rst;
    }

    /**
     * 初始化GRP系统，每次系统启动时调用
     */
    public static void initializeGrp() {
        if (null == client) {
            ConnectParam connectParam = new ConnectParam.Builder().withHost("192.168.2.68").withPort(19530).build();
            client = new MilvusGrpcClient(connectParam);
        }
        if (null == carCllxzfl || null == carCllxfl || null == busCllxfl || null == truckCllxfl) {
            System.out.println("MgqEngine.initialize 2.1");
            // 客车初始化
            busCllxfl = new ArrayList<>();
            busCllxfl.add("11");
            busCllxfl.add("12");
            busCllxfl.add("13"); // 仅包括134
            busCllxfl.add("14");
            // 轿车初始化
            carCllxzfl = new ArrayList<>();
            carCllxzfl.add("131");
            carCllxzfl.add("132");
            carCllxzfl.add("133");
            // 货车初始化
            truckCllxfl = new ArrayList<>();
            truckCllxfl.add("21");
            truckCllxfl.add("22");
            System.out.println("MgqEngine.initialize 3");
        }
    }

    /**
     * 创建图像ReID搜索库
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * ！！！！！！！！！！ 仅需要调用一次，会清空以前库中内容  ！！！！！！！！！！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     */
    public static void createGrqDb() {
        ConnectParam connectParam = new ConnectParam.Builder().
                withHost(PropUtil.getValue("MILVUS_SERVER_ADDR")).
                withPort(Integer.parseInt(PropUtil.getValue("MILVUS_SERVER_PORT"))).build();
        client = new MilvusGrpcClient(connectParam);// 创建Collection
        final String collectionName = PropUtil.getValue("GRQ_COLLECTION_NAME");
        createCollection(collectionName);
        createPartition(collectionName, PropUtil.getValue("GRQ_PN_HEAD_BUS"));
        createPartition(collectionName, PropUtil.getValue("GRQ_PN_HEAD_CAR"));
        createPartition(collectionName, PropUtil.getValue("GRQ_PN_HEAD_TRUCK"));
        createPartition(collectionName, PropUtil.getValue("GRQ_PN_TAIL_BUS"));
        createPartition(collectionName, PropUtil.getValue("GRQ_PN_TAIL_CAR"));
        createPartition(collectionName, PropUtil.getValue("GRQ_PN_TAIL_TRUCK"));
    }

    public static void createCollection(String collectionName) {
//        if (client.listCollections().contains(collectionName)) {
//            client.dropCollection(collectionName);
//        }
//        logger.info("删除已有Collection");
//        final int dimension = Integer.parseInt(PropUtil.getValue("REID_DIM")); // ReID特征向量维数
//        CollectionMapping collectionMapping =
//                CollectionMapping.create(collectionName)
//                        .addField(PropUtil.getValue("GRQ_TVIS_JSON_ID"), DataType.INT64) // tvisJsonId
//                        .addField(PropUtil.getValue("GRQ_VEHS_IDX"), DataType.INT64) // vehsIdx
//                        .addVectorField("embedding", DataType.VECTOR_FLOAT, dimension)
//                        .setParamsInJson("{\"segment_row_limit\": 4096, \"auto_id\": false}");
//        client.createCollection(collectionMapping);
//        // Check the existence of collection
//        if (!client.hasCollection(collectionName)) {
//            throw new AssertionError("创建Collection失败：Collection not found!");
//        }
    }

    public static void createPartition(String collectionName, String partitionTag) {
//        client.createPartition(collectionName, partitionTag);
//        if (!client.hasPartition(collectionName, partitionTag)) {
//            throw new AssertionError("创建" + partitionTag + "分区失败：Partition not found!");
//        }
    }
}
