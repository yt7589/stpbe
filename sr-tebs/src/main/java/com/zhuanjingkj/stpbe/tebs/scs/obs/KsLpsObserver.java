package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class KsLpsObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Override
    public void notifyObserver(VehicleVo vo) {
        Integer hour = LocalDateTime.now().getHour();
        Integer index = 0;
//        if(hour % 2 == 0) {
//            index = (hour + 2)/2 - 1;
//        } else {
//            index = (hour + 1)/2 - 1;
//        }
        index = hour + 1;
        String hType = vo.getVehicleHptzVO().getHpzt(); //牌照异常
        if(!"1".equals(hType)) {
            String tblName = AppRegistry.tvisJsonTblName;
            String imageHash = dkRtvrMapper.getImageHash(vo.getTvisJsonId(), tblName);
            //分时间段统计
            Integer count = (int)(redisTemplate.opsForList().index("ks_lps_time",index));
            redisTemplate.opsForList().set("ks_lps_time",index,count + 1);
            //过车牌照异常记录
//            Map<String, Object> map = new HashMap<>();
//            map.put("cameraId", vo.getCameraId());
//            map.put("hphm", vo.getVehicleHptzVO().getHphm());
//            map.put("time", (LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//            map.put("image", imageHash);
//            redisTemplate.opsForList().rightPush("ks_lps", map);
            //分区域统计
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000001", 1);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000002", 2);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000003", 3);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000004", 4);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000005", 5);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000006", 6);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000007", 7);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000008", 8);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000009", 9);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000010", 10);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000011", 11);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000012", 12);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000013", 13);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000014", 14);
            redisTemplate.opsForHash().increment("ks_lps_area", "C0000015", 10);
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

//        if(!redisTemplate.hasKey("ks_lps_wp")) {
//            redisTemplate.opsForValue().increment("ks_lps_wp", 0);
//        }
//
//        if(!redisTemplate.hasKey("ks_lps_tp")) {
//            redisTemplate.opsForValue().increment("ks_lps_tp", 0);
//        }
//
//        if(!redisTemplate.hasKey("ks_lps_jp")) {
//            redisTemplate.opsForValue().increment("ks_lps_jp", 0);
//        }
//
//        if(!redisTemplate.hasKey("ks_lps_hpzd")) {
//            redisTemplate.opsForValue().increment("ks_lps_hpzd", 0);
//        }
    }
}
