package com.zhuanjingkj.stpbe.tvis_server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.SubmitImageDTO;
import com.zhuanjingkj.stpbe.tvis_server.service.IStpImageService;
import com.zhuanjingkj.stpbe.tvis_server.vo.TvisImageErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StpImageService implements IStpImageService {
    private final static String LIST_VEHICLE_RECOGNITION = "vehicle-recognition-list";
    private final static Logger logger = LoggerFactory.getLogger(StpImageService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource(name = "redisTemplate2")
    private RedisTemplate<String, byte[]> redisTemplate2;
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Override
    public ResultDTO<SubmitImageDTO> submitImage(String cameraId, String gcxh, String mrhpt, String hphm, byte[] imageData) {
        logger.info("#Yt#: step 1");
        String response = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, LIST_VEHICLE_RECOGNITION, imageData);
        logger.info("#Yt#: step 2 response: " + response + "!");
        long tvisJsonId = 0; //Long.parseLong(jo.getString("tvisJsonId"));
        StringBuilder msg = null;
        synchronized (redisTemplate) {
            tvisJsonId = redisTemplate.opsForValue().increment(AppConst.TVIS_JSON_TBL_ID_KEY);
            msg = new StringBuilder("{\"cameraId\":" + cameraId + ", \"tvisJsonId\": "
                    + tvisJsonId + ", \"json\": " + response + "}");
        }
        ResultDTO<SubmitImageDTO> rst = new ResultDTO<>();
        SubmitImageDTO data = new SubmitImageDTO();
        rst.setData(data);
        if(StringUtils.equals(response,"0")){
            logger.info("#Yt#: step 3");
            rst.setCode(4);
            data.setTvisJsonId(-1);
            return rst;
        }
        logger.info("#Yt#: step 4");
        JSONObject jo = JSON.parseObject(response);
        data.setTvisJsonId(tvisJsonId);
        return rst;
    }
}
