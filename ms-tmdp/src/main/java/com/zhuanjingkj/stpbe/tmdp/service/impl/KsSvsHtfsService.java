package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsHtfsDTO;
import com.zhuanjingkj.stpbe.common.mapper.KsSvsHtfsMapper;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsHtfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KsSvsHtfsService implements IKsSvsHtfsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KsSvsHtfsMapper ksSvsHtfsMapper;

    @Override
    public KsSvsHtfsDTO getKsSvsHtfsDTO_exp() {
        KsSvsHtfsDTO htfs = new KsSvsHtfsDTO();
        Integer devNum = ksSvsHtfsMapper.getDevice();
        Integer warnNum = ksSvsHtfsMapper.getWarnNum(LocalDate.now());
        List<String> vt = ksSvsHtfsMapper.getVehicleType();
        List<String> kc = ksSvsHtfsMapper.getKaCameraCode();
        Integer total = 0;
        if(vt != null && vt.size() >0) {
            for(int i = 0; i < vt.size(); i++) {
                if(redisTemplate.hasKey(vt.get(i))) {
                    Integer tl = (int)redisTemplate.opsForValue().get(vt.get(i));
                    total = total + tl;
                }
            }
        }
        Integer kakvNum = 0;
        if(kc != null && kc.size() > 0) {
            for(int i = 0; i < kc.size(); i++) {
                if(redisTemplate.opsForHash().get("ks_svs_area", kc.get(i)) != null) {
                    Integer tl = (int) redisTemplate.opsForHash().get("ks_svs_area", kc.get(i));
                    kakvNum = kakvNum + tl;
                }
            }
        }
        htfs.setTodaySvNum(total);
        htfs.setTodayDevNum(devNum);
        htfs.setTodayWarnNum(warnNum);
        htfs.setTodayKakvNum(kakvNum);
        return htfs;
    }
}
