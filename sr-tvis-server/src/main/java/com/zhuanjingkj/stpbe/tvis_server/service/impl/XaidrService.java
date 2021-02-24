package com.zhuanjingkj.stpbe.tvis_server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.tvis_server.service.IXaidrService;
import com.zhuanjingkj.stpbe.tvis_server.vo.TvisImageErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class XaidrService implements IXaidrService {
    private final static String LIST_XAIDR_FUNCTION_DETECT = "xaidr-function-detect";
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
        return JSON.parseArray(response);
    }
}
