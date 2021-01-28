package com.zhuanjingkj.stpbe.tebs.scs;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.vo.TvisJsonVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 * 将JSON文件保存到IPFS系统中去，将信息保存到数据库中
 */
public class TvisJsonRawListener {
    public final static Logger logger = LoggerFactory.getLogger(TvisJsonRawListener.class);
    private static boolean isInitialized = false;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    private void initialize() {
        if (isInitialized) {
            return ;
        }
        TvisUtil.rotateTvisJsonTbl(tvisJsonMapper);
        isInitialized = true;
    }

    @KafkaListener(id = "TvisJsonRawListener", topics = "tvis")
    public void listen(String json) {
        System.out.println("###### TvisJsonRawListener.listen 1");
        if (!isInitialized) {
            synchronized (logger) {
                initialize();
            }
        }
        TvisUtil.processRawTvisJson(redisTemplate, tvisJsonMapper, json);
    }
}
