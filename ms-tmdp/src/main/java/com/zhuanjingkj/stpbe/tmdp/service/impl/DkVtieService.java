package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTitfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTitfItemDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVtieDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVtpDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkVtieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DkVtieService implements IDkVtieService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DkVtieDTO getDkVtie() {
        DkVtieDTO data = new DkVtieDTO();
        int ti = (Integer)redisTemplate.opsForValue().get("dkInternalNum");
        int te = (Integer)redisTemplate.opsForValue().get("dkExternalNum");
        data.setInternalPercent((int)(ti/(ti+te+0.001)*100));
        data.setExternalPercent((int)(te/(ti+te+0.001)*100));
        return data;
    }

}
