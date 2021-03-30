package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KsLpsObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void notifyObserver(VehicleVo vo) {
        System.out.println("KsLpsObserver...");
        Integer hour = LocalDateTime.now().getHour();
        Integer index = 0;
//        if(hour % 2 == 0) {
//            index = (hour + 2)/2 - 1;
//        } else {
//            index = (hour + 1)/2 - 1;
//        }
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

        index = hour + 1;
        String hType = vo.getVehicleHptzVO().getHpzt(); //牌照异常
        if(!"1".equals(hType)) {
            //分时间段统计
            Integer count = (int)(redisTemplate.opsForList().index("ks_lps_time",index));
            redisTemplate.opsForList().set("ks_lps_time",index,count + 1);
            //分区域统计
            redisTemplate.opsForHash().increment("ks_lps_area", code, 1);

        }
        //分类型统计
        if("0".equals(hType)) {
            redisTemplate.opsForValue().increment("ks_lps_wp", 1); //无牌照
        } else if ("9".equals(hType)) {
            redisTemplate.opsForValue().increment("ks_lps_tp",1); //套牌照
        } else if ("9".equals(hType)) {
            redisTemplate.opsForValue().increment("ks_lps_jp",1); //假牌照
        } else if ("2".equals(hType)) {
            redisTemplate.opsForValue().increment("ks_lps_hpzd",1); //号牌遮挡
        }
    }

    @Override
    public void initialize(Environment env) {
        if(!redisTemplate.hasKey("ks_lps_time")) {
            redisTemplate.opsForList().rightPushAll("ks_lps_time", 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(!redisTemplate.hasKey("ks_lps")) {
            redisTemplate.opsForList().rightPushAll("ks_lps", 0);
        }
        if (!redisTemplate.hasKey("ks_lps_wp")) {
            redisTemplate.opsForValue().set("ks_lps_wp", 0); //无牌照
        }
        if (!redisTemplate.hasKey("ks_lps_tp")) {
            redisTemplate.opsForValue().set("ks_lps_tp",0); //套牌照
        }

        if (!redisTemplate.hasKey("ks_lps_jp")) {
            redisTemplate.opsForValue().set("ks_lps_jp",0); //假牌照
        }

        if (!redisTemplate.hasKey("ks_lps_hpzd")) {
            redisTemplate.opsForValue().set("ks_lps_hpzd",0); //号牌遮挡
        }
    }
}
