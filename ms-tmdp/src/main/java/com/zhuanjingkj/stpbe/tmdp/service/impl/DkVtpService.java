package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.DkVtpDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkVtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DkVtpService implements IDkVtpService {

    public final static String DK_VT_CAR = "小汽车";
    public final static String DK_VT_SUV = "SUV";
    public final static String DK_VT_MPV = "MPV";
    public final static String DK_VT_VAN = "面包车";
    public final static String DK_VT_TANK_TRUCK = "罐式货车";
    public final static String DK_VT_NORMAL_TRUCK = "普通货车";
    public final static String DK_VT_OTHERS = "其他";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DkVtpDTO getDkVtp() {
        Map<String,  Integer> percents = new HashMap<>();
        percents.put("小汽车", 10);
        percents.put("SUV", 20);
        percents.put("MPV", 30);
        percents.put("面包车", 40);
        percents.put("厢式货车", 50);
        percents.put("普通货车", 60);
        //DkVtpDTO dkVtp = new DkVtpDTO();
        //dkVtp.setPercents(percents);
        return null;
    }

    @Override
    public List<DkVtpDTO> getDkVtpDTOs_exp() {
        List<DkVtpDTO> vtps = new ArrayList<>();
        DkVtpDTO item = null;
        int car = (Integer)(redisTemplate.opsForValue().get("DK_VT_CAR") == null ? 0 : redisTemplate.opsForValue().get("DK_VT_CAR"));
        int suv = (Integer)(redisTemplate.opsForValue().get("DK_VT_SUV") == null ? 0 : redisTemplate.opsForValue().get("DK_VT_SUV"));
        int mpv = (Integer)(redisTemplate.opsForValue().get("DK_VT_MPV") == null ? 0 : redisTemplate.opsForValue().get("DK_VT_MPV"));
        int van = (Integer)(redisTemplate.opsForValue().get("DK_VT_VAN") == null ? 0 : redisTemplate.opsForValue().get("DK_VT_VAN"));
        int tTruck = (Integer)(redisTemplate.opsForValue().get("DK_VT_TANK_TRUCK") == null ? 0 : redisTemplate.opsForValue().get("DK_VT_TANK_TRUCK"));
        int nTruck = (Integer)(redisTemplate.opsForValue().get("DK_VT_NORMAL_TRUCK") == null ? 0 : redisTemplate.opsForValue().get("DK_VT_NORMAL_TRUCK"));
        int others = (Integer)(redisTemplate.opsForValue().get("DK_VT_OTHERS") == null ? 0 : redisTemplate.opsForValue().get("DK_VT_OTHERS"));
        int total = car + suv + mpv + van + tTruck + nTruck + others;
        item = new DkVtpDTO(DK_VT_CAR, (int)(car/(total+0.001)*100));
        vtps.add(item);
        item = new DkVtpDTO(DK_VT_SUV, (int)(suv/(total+0.001)*100));
        vtps.add(item);
        item = new DkVtpDTO(DK_VT_MPV, (int)(mpv/(total+0.001)*100));
        vtps.add(item);
        item = new DkVtpDTO(DK_VT_VAN, (int)(van/(total+0.001)*100));
        vtps.add(item);
        item = new DkVtpDTO(DK_VT_TANK_TRUCK,(int)(tTruck/(total+0.001)*100));
        vtps.add(item);
        item = new DkVtpDTO(DK_VT_NORMAL_TRUCK, (int)(nTruck/(total+0.001)*100));
        vtps.add(item);
        item = new DkVtpDTO(DK_VT_OTHERS, (int)(others/(total+0.001)*100));
        vtps.add(item);
        return vtps;
    }
}
