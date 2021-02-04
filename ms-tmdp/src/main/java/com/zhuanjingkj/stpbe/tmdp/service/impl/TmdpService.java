package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.tvis.TvisStpOberverManager;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.SubmitImageDTO;
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
    private final static String LIST_VEHICLE_RECOGNITION = "vehicle-recognition-list";
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
    public ResultDTO<WsmVideoFrameDTO> getTvisAnalysisResult(long cameraId, long baseTvisJsonId, int direction) {
        ResultDTO<WsmVideoFrameDTO> dto = new ResultDTO<>();
        logger.info("call TvisUtil.getTvisImageAnalysisResult");
        WsmVideoFrameDTO vfv = TvisUtil.getTvisImageAnalysisResult(tvisJsonMapper, cameraId, baseTvisJsonId, direction);
        dto.setData(vfv);
        return dto;
    }

    @Override
    public ResultDTO<SubmitImageDTO> recognizeTvisImage(String cameraId, String gcxh, String mrhpt, String hphm, byte[] imageData, String imageFile) {
        String streamId = "-1";
        ResultDTO<SubmitImageDTO> rst = new ResultDTO<>();
        SubmitImageDTO data = TvisUtil.recognizeTvisImage(environment, redisTemplate, redisTemplate2,
                tvisJsonMapper, tvisStpOberverManager, LIST_VEHICLE_RECOGNITION,
                cameraId, streamId,
                imageFile, imageData);
        rst.setData(data);
        if (data.getTvisJsonId() < 0) {
            rst.setCode(4);
            return rst;
        }
        return rst;
    }
}
