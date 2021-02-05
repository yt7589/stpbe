package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.tvis.TvisStpOberverManager;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.RecognizeTvisImageDTO;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameDTO;
import com.zhuanjingkj.stpbe.tmdp.service.ITmdpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TmdpService implements ITmdpService {
    private final static Logger logger = LoggerFactory.getLogger(TmdpService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource(name = "redisTemplate2")
    private RedisTemplate<String, byte[]> redisTemplate2;
    @Autowired
    private TvisJsonMapper tvisJsonMapper;
    @Autowired
    private TvisStpOberverManager tvisStpOberverManager;
    @Autowired
    private Environment environment;

    @Override
    public ResultDTO<WsmVideoFrameDTO> getTvisAnalysisResult(long cameraId,
                                                             long baseTvisJsonId,
                                                             int direction) {
        ResultDTO<WsmVideoFrameDTO> dto = new ResultDTO<>();
        logger.info("call TvisUtil.getTvisImageAnalysisResult");
        WsmVideoFrameDTO vfv = TvisUtil.getTvisImageAnalysisResult(tvisJsonMapper,
                cameraId, baseTvisJsonId, direction);
        dto.setData(vfv);
        return dto;
    }

    @Override
    public ResultDTO<RecognizeTvisImageDTO> recognizeTvisImage(String cameraId, String gcxh,
                                                               String mrhpt, String hphm,
                                                               byte[] imageData, String imageFile) {
        String jsonResult = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2,
                AppConst.LIST_VEHICLE_RECOGNITION, imageData);
        ResultDTO<RecognizeTvisImageDTO> rst = new ResultDTO<>();
        RecognizeTvisImageDTO data = new RecognizeTvisImageDTO();
        rst.setData(data);
        data.setTvisJsonId(0);
        data.setJsonResult(jsonResult);
        return rst;
    }
}
