package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.AppRegistry;
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
        long ti = (Long)redisTemplate.opsForValue().get("dkInternalNum");
        long te = (Long)redisTemplate.opsForValue().get("dkExternalNum");
        data.setInternalPercent((int)(ti/(ti+te+0.001)*100));
        data.setExternalPercent((int)(te/(ti+te+0.001)*100));
        return data;
    }
}
