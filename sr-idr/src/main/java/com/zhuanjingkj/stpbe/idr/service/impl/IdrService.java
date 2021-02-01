package com.zhuanjingkj.stpbe.idr.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.idr.service.IIdrService;
import com.zhuanjingkj.stpbe.idr.vo.IdrErrorResponse;
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
import java.util.Map;

@Service
public class IdrService implements IIdrService {
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

    private final static Logger logger = LoggerFactory.getLogger(IdrService.class);

    @Override
    public Map<String, Object> recognition(String cameraId, String gcxh, String mrhpt, String hphm, byte[] imageData) {
        String response = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, LIST_VEHICLE_RECOGNITION, imageData);
        if(StringUtils.equals(response,"0")){
            IdrErrorResponse responseError = new IdrErrorResponse(4,gcxh,MSG);
            return JSON.parseObject(JSON.toJSONString(responseError));
        }
        // 向Kafka的Topic发送请求
        StringBuilder msg = new StringBuilder("{\"cameraId\": \"" + cameraId + "\", \"json\": " + response + "}");
        kafkaTemplate.send("tvis", 0, msg.toString());
        kafkaTemplate.flush();
        return JSON.parseObject(response);
    }
}
