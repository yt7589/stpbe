package com.zhuanjingkj.stpbe.tvis_server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.common.tvis.TvisStpOberverManager;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.SubmitImageDTO;
import com.zhuanjingkj.stpbe.tvis_server.dto.TvisAnalysisItemDTO;
import com.zhuanjingkj.stpbe.tvis_server.dto.TvisAnalysisResultDTO;
import com.zhuanjingkj.stpbe.tvis_server.service.IStpImageService;
import com.zhuanjingkj.stpbe.tvis_server.vo.TvisImageErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StpImageService implements IStpImageService {
    private final static String LIST_VEHICLE_RECOGNITION = "vehicle-recognition-list";
    private final static Logger logger = LoggerFactory.getLogger(StpImageService.class);

    private static List<ITvisStpObserver> observers = new ArrayList<>();
    private static boolean isFirstRun = true;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource(name = "redisTemplate2")
    private RedisTemplate<String, byte[]> redisTemplate2;
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;
    @Autowired
    private TvisJsonMapper tvisJsonMapper;
    @Autowired
    private TvisStpOberverManager tvisStpOberverManager;
    @Autowired
    private Environment environment;

    @Override
    public ResultDTO<SubmitImageDTO> submitImage(String cameraId, String gcxh, String mrhpt, String hphm, byte[] imageData, String imageFile) {
        String rawResp = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, LIST_VEHICLE_RECOGNITION, imageData);
        JSONObject jo = JSONObject.parseObject(rawResp);
        jo.put("ImageUrl", imageFile);
        jo.put("StreamID", "-1");
        String response = jo.toJSONString();
        long tvisJsonId = 0;
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
            rst.setCode(4);
            data.setTvisJsonId(-1);
            return rst;
        }
        if (isFirstRun) {
            TvisUtil.rotateTvisJsonTbl(tvisJsonMapper);
            tvisStpOberverManager.initialize(observers, environment);
            isFirstRun = false;
        }
        logger.info("#Yt#: json: " + msg.toString() + "!");
        TvisUtil.processRawTvisJson(redisTemplate, tvisJsonMapper, msg.toString());
        TvisUtil.processStpTvisJson(observers, msg.toString());
        data.setTvisJsonId(tvisJsonId);
        return rst;
    }

    /**
     * 获取指定摄像头的识别结果供前端进行显示（路网实况=》图像分析中）
     * @param cameraId 摄像头编号
     * @param baseTvisJsonId 参考识别结果编号
     * @param direction 0：最新；1：前一张；2：后一张
     * @return
     */
    @Override
    public ResultDTO<TvisAnalysisResultDTO> getTvisAnalysisResult(long cameraId, long baseTvisJsonId, int direction) {
        ResultDTO<TvisAnalysisResultDTO> dto = new ResultDTO<>();
        TvisAnalysisResultDTO data = new TvisAnalysisResultDTO();
        data.setTvisJsonId(108);
        data.setOriginImageUrl("http://a.jpg");
        TvisAnalysisItemDTO item = null;
        List<TvisAnalysisItemDTO> items = new ArrayList<>();
        for (int i=0; i<5; i++) {
            item = new TvisAnalysisItemDTO(100 + i, 200 + i,
                    "http://b" + i + ".jpg", "京00" + i,
                    "5" + i + "秒前", "无违章" + i);
            items.add(item);
        }
        data.setItems(items);
        dto.setData(data);
        return dto;
    }
}
