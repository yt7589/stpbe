package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class KsAsObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void notifyObserver(VehicleVO vo) {
        System.out.println("KsAsObserver...");
        /**
         * cameraId = -1 时需要根据streamId查找正确的cameraId
         */
        long cameraId = vo.getCameraId();
        String code = "";
        if(cameraId == -1) {
            long streamId = vo.getStreamId();
            String newCameraId = deviceMapper.getCameraIdByStreamId(streamId);
            if(StringUtils.isNotBlank(newCameraId)) {
                code = newCameraId;
            }
        } else {
            code = cameraId + "";
        }
        String hphm = vo.getVehicleHptzVO().getHphm();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //统计同一辆车在同一个设备下通过的次数
        if(StringUtils.isBlank(hphm)) {
            hphm = "NULL";
        }
        /**
         * 只保存重点区域监控到的车辆信息
         */
        Map<String, Object> resMap = deviceMapper.getKeyArea(code);
        if(resMap != null && resMap.size() > 0) {
            redisTemplate.opsForHash().increment("ks_as_lsvs_total",  hphm + "|" + code, 1);
            redisTemplate.opsForHash().put("ks_as_lsvs_time", hphm + "|" + code, date);
            redisTemplate.opsForList().leftPush("ks_as_lsvs_list", hphm + "|" + code);
        }

    }

    @Override
    public void initialize(Environment env) {
        if(!redisTemplate.hasKey("ks_as_lsvs_list")) {
            redisTemplate.opsForList().rightPushAll("ks_as_lsvs_list","0");
        }
    }
}
