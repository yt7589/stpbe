package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.service.IVideoAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoAnalysisService implements IVideoAnalysisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public final static String WSS_ID = "wssId";

    private static Map<String, List<String>> orgImages = new HashMap<>();

    private static Map<String, List<String>> cutImages = new HashMap<>();

    @Override
    public long registerWs(WebSocketSession wss) {
        long wssId = redisTemplate.opsForValue().increment(WSS_ID);
        orgImages.put("" + wssId, new ArrayList<>());
        cutImages.put("" + wssId, new ArrayList<>());
        return wssId;
    }
}
