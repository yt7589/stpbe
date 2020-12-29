package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVtieDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkVtieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DkVtieService implements IDkVtieService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DkVtieDTO getDkVtie() {
        DkVtieDTO data = new DkVtieDTO();
        System.out.println("######## incr=" + redisTemplate.opsForValue().increment("dkInternalNum", 0) + "!");
        long ti = redisTemplate.opsForValue().increment("dkInternalNum", 0);
        long te = redisTemplate.opsForValue().increment("dkExternalNum", 0);
        System.out.println("########## ti=" + ti + "; te=" + te + "!");
        data.setInternalPercent((int)(ti/(ti+te+0.001)));
        data.setExternalPercent((int)(te/(ti+te+0.001)));
        return data;
    }
}
