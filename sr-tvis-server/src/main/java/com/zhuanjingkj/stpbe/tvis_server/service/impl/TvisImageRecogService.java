package com.zhuanjingkj.stpbe.tvis_server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.tvis_server.service.ITvisImageRecogService;
import com.zhuanjingkj.stpbe.tvis_server.vo.TvisImageErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TvisImageRecogService implements ITvisImageRecogService {
    private final static String LIST_CREATE_LIB = "create-lib-list";
    private final static String LIST_QUERY_LIB = "query-lib-list";
    private final static String LIST_UPDATE_LIB = "update-lib-list";
    private final static String LIST_VEHICLE_RECOGNITION = "vehicle-recognition-list";
    private final static String LIST_VEHICLE_COMPARE = "vehicle-compare-list";
    private final static String LIST_VEHICLE_COMPARE_IN_LIB = "vehicle-compare-in-lib-list";
    private final static int REQUEST_ID_LEN = 36;
    private final static int REQUEST_EXPIRED_TIME = 1 * 60 * 1000;
    private final static String MSG = "图片无法识别";
    private volatile int num = 0;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource(name = "redisTemplate2")
    private RedisTemplate<String, byte[]> redisTemplate2;
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Value("${result.timeout:10000}")
    private long timeout;

    private final static Logger logger = LoggerFactory.getLogger(TvisImageRecogService.class);

    @Override
    public Map<String, Object> createLib(String name) {
        String response = sendStringRequest(LIST_CREATE_LIB, name);
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> queryLib(String id) {
        String response = sendStringRequest(LIST_QUERY_LIB, id);
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> updateLib(Map<String, Object> params) {
        String response = sendStringRequest(LIST_UPDATE_LIB, JSON.toJSONString(params));
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> recognition(String cameraId, String gcxh, String mrhpt, String hphm, byte[] imageData) {
        String response = sendByteRequest(LIST_VEHICLE_RECOGNITION, imageData);
        if(StringUtils.equals(response,"0")){
            TvisImageErrorResponse responseError = new TvisImageErrorResponse(4,gcxh,MSG);
            return JSON.parseObject(JSON.toJSONString(responseError));
        }
        // 向Kafka的Topic发送请求
        StringBuilder msg = new StringBuilder("{\"cameraId\": \"" + cameraId + "\", \"json\": " + response + "}");
        kafkaTemplate.send("tvis", 0, msg.toString());
        kafkaTemplate.flush();
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> compareVehicle(String cltzxx1, String cltzxx2) {
        String response = sendStringRequest(LIST_VEHICLE_COMPARE, joinParams(cltzxx1, cltzxx2));
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> compareInLib(String cltzxx, String kid, String xsdyz, String fydx, String ys) {
        String response = sendStringRequest(LIST_VEHICLE_COMPARE_IN_LIB, joinParams(cltzxx, kid, xsdyz, fydx, ys));
        return JSON.parseObject(response);
    }



    private String joinParams(String... param) {
        return String.join("||", param);
    }

    private String sendByteRequest(String requestQueue, byte[] data) {
        String requestId = UUID.randomUUID().toString();

        byte[] id = requestId.getBytes(Charset.forName("UTF-8"));
        byte[] requestData = new byte[id.length + data.length];
        System.arraycopy(id, 0, requestData, 0, id.length);
        System.arraycopy(data, 0, requestData, id.length, data.length);
        data = null;
        return sendRequest(requestQueue, requestId, requestData);
    }

    private String sendStringRequest(String requestQueue, String request) {
        String requestId = UUID.randomUUID().toString();
        return sendRequest(requestQueue, requestId, joinParams(requestId, request));
    }

    private String sendMapRequest(String requestQueue, Map<String, Object> request) {
        String requestId = UUID.randomUUID().toString();
        request.put("requestId", requestId);
        return sendRequest(requestQueue, requestId, JSON.toJSONString(request));
    }

    private final static String REQUEST_ID_PREFIX = "a_";
    private String sendRequest(String requestList, String requestId, Object requestData) {
        if (requestData instanceof String) {
            redisTemplate.opsForList().leftPush(requestList, (String) requestData);
        } else {
            /*
            synchronized (this){
                logger.info("sendRequest 3.1");
                // 设置请求编号超时时间
                redisTemplate.opsForValue().set(REQUEST_ID_PREFIX + requestId, "0", REQUEST_EXPIRED_TIME, TimeUnit.MILLISECONDS);
                logger.info("sendRequest 3.2");
                // 取出最老请求的请求编号
                long rlSize = redisTemplate2.opsForList().size(requestList);
                logger.info("sendRequest 3.3 rlSize=" + rlSize + "!");
                byte[] topVal;
                if(rlSize > 0){
                    logger.info("sendRequest 3.4");
                    topVal = redisTemplate2.opsForList().range(requestList, rlSize-1, rlSize).get(0);
                    logger.info("sendRequest 3.5");
                    StringBuilder oldRequestId = new StringBuilder(REQUEST_ID_PREFIX);
                    String oldRawRequestId = new String(topVal, 0, REQUEST_ID_LEN, Charset.forName("UTF-8"));
                    oldRequestId.append(new String(topVal, 0, REQUEST_ID_LEN, Charset.forName("UTF-8")));
                    logger.info("sendRequest 3.6 oldRequestId=" + oldRequestId + "!");
                    // 如果最老请求编号在Redis中不存在，证明该请求已过期，则删除该请求，继续比较接下来的元素
                    while (redisTemplate.opsForValue().get(oldRequestId.toString()) == null) {
                        logger.info("sendRequest 3.7");
                        redisTemplate2.opsForList().rightPop(requestList);
                        logger.info("sendRequest 3.8");
                        rlSize = redisTemplate2.opsForList().size(requestList);
                        logger.info("sendRequest 3.9");
                        if(rlSize<=0)
                            break;
                        logger.info("sendRequest 3.10");
                        topVal = redisTemplate2.opsForList().range(requestList, rlSize-1, rlSize).get(0);
                        logger.info("sendRequest 3.11");
                        oldRequestId = new StringBuilder(REQUEST_ID_PREFIX);
                        oldRequestId.append(new String(topVal, 0, 36, Charset.forName("UTF-8")));
                        logger.info("sendRequest 3.12");
                    }
                }
                redisTemplate2.opsForList().leftPush(requestList, (byte[]) requestData);
            }*/
            redisTemplate2.opsForList().leftPush(requestList, (byte[]) requestData);
        }

        long startTime = System.currentTimeMillis();
        String response = null;
        do {
            try {
                Thread.sleep(3);
            } catch (InterruptedException ignore) {
            }
            response = redisTemplate.opsForValue().get(requestId);
            if (response != null) {
                break;
            }
        } while (System.currentTimeMillis() - startTime < timeout);

        if (response == null) {
            //throw new RuntimeException("等待执行结果超时");
            response = "{\"timestamp\":\"2020-11-26T08:29:34.273+0000\",\"status\":404,\"error\":\"Not Found\",\"message\":\"No message available\",\"path\":\"/image/function/recognition\"}";
        }

        return response;
    }

    private String testData = "{\n" +
            "    \"iPicNum\": 1,\n" +
            "    \"iRecognitionTime\": 0,\n" +
            "    \"iPicResult\": [\n" +
            "        {\n" +
            "            \"iVehicleNum\": 1,\n" +
            "            \"iVehicleInfo\": [\n" +
            "                {\n" +
            "                    \"iVehicleRect\": {\n" +
            "                        \"iRight\": 2448,\n" +
            "                        \"iBottom\": 1261,\n" +
            "                        \"iLeft\": 1756,\n" +
            "                        \"iTop\": 470\n" +
            "                    },\n" +
            "                    \"iVehicleExpandRect\": {\n" +
            "                        \"iExpandTop\": 352,\n" +
            "                        \"iExpandBottom\": 1378,\n" +
            "                        \"iExpandLeft\": 1652,\n" +
            "                        \"iExpandRight\": 2550\n" +
            "                    },\n" +
            "                    \"iVehicleDIR\": 1,\n" +
            "                    \"iVehicleClass\": \"Car_SUV\",\n" +
            "                    \"iVehicleClassConf\": 52,\n" +
            "                    \"iVehicleColor1\": 8,\n" +
            "                    \"iVehicleColor2\": 8,\n" +
            "                    \"iVehicleTypeConf\": 41,\n" +
            "                    \"iVehicleType\": \"讴歌牌-TL-2010\",\n" +
            "                    \"iPlateFlag\": 1,\n" +
            "                    \"iPlateInfor\": {\n" +
            "                        \"iPlateType\": 1,\n" +
            "                        \"iPlateCharConfidence\": \"84,84,84,84,84,84,84,\",\n" +
            "                        \"iPlateConfidence\": 84,\n" +
            "                        \"iPlateColor\": 1,\n" +
            "                        \"iPlateCharNum\": 7,\n" +
            "                        \"iPlateNumber\": \"粤A2KX25\",\n" +
            "                        \"iPlateCharacterStyle\": 1\n" +
            "                    },\n" +
            "                    \"iPlateRect\": {\n" +
            "                        \"iRight\": 2212,\n" +
            "                        \"iBottom\": 1214,\n" +
            "                        \"iLeft\": 2070,\n" +
            "                        \"iTop\": 1182\n" +
            "                    },\n" +
            "                    \"iFeatureExtractionNum\": 256,\n" +
            "                    \"iFeatureExtractionValue\": \"-0.004736,-0.009117,0.017604,0.002847,-0.080279,0.091496,0.002455,0.072847,0.065320,-0.022563,-0.046338,-0.013162,0.012925,-0.028899,-0.091249,0.014141,-0.025421,-0.165194,-0.132798,0.008925,0.034332,0.024720,-0.057949,0.044616,-0.129394,-0.048126,0.036669,0.003969,0.043899,0.125122,-0.026973,0.124848,-0.040882,-0.016238,-0.021337,-0.039316,0.143754,0.116458,0.010822,-0.166337,0.036686,-0.022530,0.092644,-0.052196,0.006544,-0.056185,0.119146,0.082312,-0.021820,-0.040381,0.070140,-0.002210,-0.080928,0.082387,-0.029214,0.077987,0.033872,-0.061785,0.032423,-0.041847,0.027796,0.017719,0.011347,0.079459,-0.062602,-0.008496,-0.013449,0.073008,0.092493,-0.066574,-0.000637,0.018556,0.018427,-0.011739,0.004314,-0.036088,-0.052712,-0.064594,-0.045356,0.094697,-0.138997,0.016414,-0.148863,-0.039325,0.072336,-0.029754,0.010638,0.024258,0.065895,0.016312,-0.098799,0.053847,0.028108,0.133353,0.058159,-0.088962,0.001395,-0.043363,-0.073038,0.061902,-0.098152,-0.016723,-0.062046,-0.014858,-0.071603,-0.012566,0.050031,-0.002213,-0.161688,0.004262,0.035987,-0.059261,0.031346,-0.099031,0.052418,-0.015768,-0.066318,-0.001644,-0.007397,0.044578,0.130484,0.153578,0.002293,0.055464,0.091593,0.032596,0.069732,0.012127,0.127695,0.063163,0.021672,-0.021604,-0.035944,0.034970,0.048324,-0.081469,0.020646,-0.011381,-0.047976,-0.038407,0.184172,-0.040599,0.023476,-0.016375,0.079724,0.018155,-0.111196,-0.060977,-0.051530,-0.084692,0.026562,-0.024662,-0.066117,-0.036017,0.018425,0.041388,0.057794,0.062771,0.014153,0.042894,0.063835,-0.000578,0.011749,-0.073878,-0.005416,-0.005216,-0.017069,-0.017454,0.078932,0.046055,0.029811,-0.020678,-0.065994,-0.014385,-0.001107,-0.001906,-0.027139,-0.063589,-0.087001,0.004225,-0.042582,0.044921,-0.051274,-0.002426,-0.045963,-0.036237,-0.057392,0.018059,-0.097112,-0.083280,-0.027466,0.139984,0.097408,-0.126585,-0.072742,0.008992,-0.015604,0.001819,0.034195,-0.013239,-0.100957,0.009222,0.039799,-0.088831,-0.003210,0.000264,-0.060291,0.098869,-0.061370,0.021753,-0.088558,0.076435,-0.010493,-0.002137,0.024360,0.126405,0.084680,-0.002195,-0.071390,-0.027403,-0.060572,-0.010980,-0.031743,-0.031874,-0.078267,0.031621,0.006329,0.009685,-0.011428,0.034783,-0.144949,0.026394,-0.075646,-0.004863,-0.017877,-0.088773,0.085208,-0.042871,-0.020958,-0.001750,-0.060451,-0.071169,-0.063290,0.107815,-0.001819,-0.053126,-0.036327,0.066076,0.046151,-0.016531,-0.013850,0.015729,-0.035006,0.071369,-0.015158,-0.033368\",\n" +
            "                    \"iVehicleXWTZ\": \"8D\",\n" +
            "                    \"iXWTZFX\": [\n" +
            "                        {\n" +
            "                            \"iXWTZFXBottom\": 802,\n" +
            "                            \"iXWTZFXLeft\": 2137,\n" +
            "                            \"iXWTZFXConfdence\": 83,\n" +
            "                            \"iXWTZFXTop\": 733,\n" +
            "                            \"iXWTZFXRight\": 2286,\n" +
            "                            \"iXWTZFXType\": \"Safety_Belt\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"iXWTZFXBottom\": 724,\n" +
            "                            \"iXWTZFXLeft\": 1904,\n" +
            "                            \"iXWTZFXConfdence\": 56,\n" +
            "                            \"iXWTZFXTop\": 705,\n" +
            "                            \"iXWTZFXRight\": 1926,\n" +
            "                            \"iXWTZFXType\": \"Mark\"\n" +
            "                        }\n" +
            "                    ]\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"iStatus\": 0\n" +
            "}";
}
