package com.zhuanjingkj.stpbe.tvis_server.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.tvis.TvisStpOberverManager;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.RecognizeTvisImageDTO;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameDTO;
import com.zhuanjingkj.stpbe.tvis_server.service.IStpImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    @Autowired
    private TvisJsonMapper tvisJsonMapper;
    @Autowired
    private TvisStpOberverManager tvisStpOberverManager;
    @Autowired
    private Environment environment;

    @Override
    public ResultDTO<RecognizeTvisImageDTO> submitImage(String cameraId, String gcxh,
                                                        String mrhpt, String hphm,
                                                        byte[] imageData, String imageFile) {
        System.out.println("StpImageService.submitImage 1");
        String streamId = "-1";
        ResultDTO<RecognizeTvisImageDTO> rst = new ResultDTO<>();
        RecognizeTvisImageDTO data = TvisUtil.submitTvisImage(environment, redisTemplate, redisTemplate2,
                tvisJsonMapper, tvisStpOberverManager, LIST_VEHICLE_RECOGNITION,
                cameraId, streamId,
                imageFile, imageData);
        System.out.println("StpImageService.submitImage 2 jsonId=" + data.getTvisJsonId() + "; result=" + data.getJsonResult() + "!!!!!!");
        rst.setData(data);
        if (data.getTvisJsonId() < 0) {
            rst.setCode(4);
            return rst;
        }
        System.out.println("StpImageService.submitImage 3");
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
    public ResultDTO<WsmVideoFrameDTO> getTvisAnalysisResult(long cameraId, long baseTvisJsonId, int direction) {
        ResultDTO<WsmVideoFrameDTO> dto = new ResultDTO<>();
        WsmVideoFrameDTO vfv = TvisUtil.getTvisImageAnalysisResult(tvisJsonMapper, cameraId, baseTvisJsonId, direction);
        /*WsmVideoFrameDTO data = new WsmVideoFrameDTO(1, 2, "http://a.com/b.jpg");
        WsmVideoFrameVehicleDTO item = null;
        List<WsmVideoFrameVehicleDTO> items = new ArrayList<>();
        // long wvfvvId, long trackId, int vehIdx, String ppcxnk, String hphm,
        //                                   String cutImgUrl, String crossTime, String trafficViolationName
        for (int i=0; i<5; i++) {
            item = new WsmVideoFrameVehicleDTO(i, 100+i, 10+i, "nk",
                    "h" + i, "c.jpg" + i,
                    "cross" + i, "tvn" + i);
            items.add(item);
        }
        data.setData(items);*/
        dto.setData(vfv);
        return dto;
    }
}
