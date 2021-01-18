package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvtitfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvtitfsDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsKsvtitfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KsSvsKsvtitfsService implements IKsSvsKsvtitfsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<KsSvsKsvtitfsDTO> getKsSvsKsvtitfsDTOs_exp() {
        List<KsSvsKsvtitfsDTO> rst = new ArrayList<>();
        List<KsSvsKsvtitfDTO> ksvtitfs = null;
        // 重点车
        ksvtitfs = new ArrayList<>();
        ksvtitfs.add(new KsSvsKsvtitfDTO("2", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 0)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("4", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 1)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("6", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 2)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("8", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 3)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("10", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 4)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("12", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 5)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("14", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 6)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("16", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 7)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("18", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 8)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("20", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 9)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("22", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 10)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("24", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_vehicle", 11)+"")));
        rst.add(new KsSvsKsvtitfsDTO("重点车", ksvtitfs));
        // 大货车
        ksvtitfs = new ArrayList<>();
        ksvtitfs.add(new KsSvsKsvtitfDTO("2", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 0)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("4", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 1)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("6", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 2)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("8", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 3)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("10", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 4)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("12", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 5)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("14", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 6)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("16", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 7)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("18", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 8)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("20", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 9)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("22", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 10)+"")));
        ksvtitfs.add(new KsSvsKsvtitfDTO("24", Long.valueOf(redisTemplate.opsForList().index("ks_ksvrp_truck", 11)+"")));
        rst.add(new KsSvsKsvtitfsDTO("大货车", ksvtitfs));
        return rst;
    }
}
