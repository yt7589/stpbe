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
        System.out.println("######## ?????? ti=" + redisTemplate.opsForValue().get("dkInternalNum0") + "!");
        long ti = Long.parseLong((String)redisTemplate.opsForValue().get("dkInternalNum0"));
        long te = Long.parseLong((String)redisTemplate.opsForValue().get("dkExternalNum0"));
        System.out.println("########## ti=" + ti + "; te=" + te + "; ti0="
                + redisTemplate.opsForValue().get("dkInternalNum") + "!");
        data.setInternalPercent((int)(ti/(ti+te+0.001)*100));
        data.setExternalPercent((int)(te/(ti+te+0.001)*100));
        return data;
    }
}
