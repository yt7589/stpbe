package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameDTO;
import com.zhuanjingkj.stpbe.tmdp.service.ITmdpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmdpService implements ITmdpService {
    private final static String LIST_VEHICLE_RECOGNITION = "vehicle-recognition-list";
    private final static Logger logger = LoggerFactory.getLogger(TmdpService.class);
    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    @Override
    public ResultDTO<WsmVideoFrameDTO> getTvisAnalysisResult(long cameraId, long baseTvisJsonId, int direction) {
        ResultDTO<WsmVideoFrameDTO> dto = new ResultDTO<>();
        logger.info("call TvisUtil.getTvisImageAnalysisResult");
        WsmVideoFrameDTO vfv = TvisUtil.getTvisImageAnalysisResult(tvisJsonMapper, cameraId, baseTvisJsonId, direction);
        dto.setData(vfv);
        return dto;
    }
}
