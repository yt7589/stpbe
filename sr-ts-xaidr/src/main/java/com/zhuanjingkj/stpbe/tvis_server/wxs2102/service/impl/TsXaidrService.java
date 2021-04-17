package com.zhuanjingkj.stpbe.tvis_server.wxs2102.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.tvis_server.wxs2102.service.ITsXaidrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TsXaidrService implements ITsXaidrService {
    private final static String LIST_XAIDR_FUNCTION_DETECT = "dcls-recognition-list";
    private final static String MSG = "图片无法识别";
    private volatile int num = 0;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource(name = "redisTemplate2")
    private RedisTemplate<String, byte[]> redisTemplate2;

    @Override
    public JSONArray detectXaidrImage(byte[] imageData) {
        String response = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, LIST_XAIDR_FUNCTION_DETECT, imageData);
        if(StringUtils.equals(response,"0")){
            return null;
        }
        System.out.println("Yantao resp: " + response + "!");
        if (response.indexOf(":404,") >= 0) {
            return new JSONArray();
        }
        return JSON.parseArray(response);
    }
}
