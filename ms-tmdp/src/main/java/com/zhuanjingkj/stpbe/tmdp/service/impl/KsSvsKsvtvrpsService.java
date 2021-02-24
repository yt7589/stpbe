package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvtvrpDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsKsvtvrpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KsSvsKsvtvrpsService implements IKsSvsKsvtvrpsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<KsSvsKsvtvrpDTO> getKsSvsKsvtvrpDTOs_exp() {
        List<KsSvsKsvtvrpDTO> ksvtvrp = new ArrayList<>();
        ksvtvrp.add(new KsSvsKsvtvrpDTO(1,  IpfsClient.getIpfsUrl("" + redisTemplate.opsForList().leftPop("ks_ksvtvrps_images"))));
        ksvtvrp.add(new KsSvsKsvtvrpDTO(2, IpfsClient.getIpfsUrl("" + redisTemplate.opsForList().leftPop("ks_ksvtvrps_images"))));

//        ksvtvrp.add(new KsSvsKsvtvrpDTO(100,"http://222.128.117.234:9003/imgs/tsclwz1.png"));
//        ksvtvrp.add(new KsSvsKsvtvrpDTO(101,"http://222.128.117.234:9003/imgs/tsclwz2.png"));
        return ksvtvrp;
    }
}
