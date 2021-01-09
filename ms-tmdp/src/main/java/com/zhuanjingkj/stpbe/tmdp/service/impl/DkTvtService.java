package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.DkTvtDTO;
import com.zhuanjingkj.stpbe.tmdp.mapper.DkTvtMapper;
import com.zhuanjingkj.stpbe.tmdp.service.IDkTvtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DkTvtService implements IDkTvtService {

    @Autowired
    private DkTvtMapper dkTvtMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<DkTvtDTO> getDkTvtDTOs_exp() {
        List<DkTvtDTO> list = new ArrayList<>();
        DkTvtDTO dkTvtDTO = null;
        List<Integer> ops = redisTemplate.opsForList().range("dk_rtvr_violation",0, -1);
        for (int i = 0; i < ops.size(); i++) {
            dkTvtDTO = new DkTvtDTO("" + (i + 1) * 2, ops.get(i));
            list.add(dkTvtDTO);
        }
        return list;
    }
}
