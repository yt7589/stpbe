package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvrpDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsKsvrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KsSvsKsvrpService implements IKsSvsKsvrpService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<KsSvsKsvrpDTO> getKsSvsKsvrpDTOs_exp() {
        List<KsSvsKsvrpDTO> list = new ArrayList<>();
//        list.add(new KsSvsKsvrpDTO(1,  IpfsClient.getIpfsUrl("" + redisTemplate.opsForList().leftPop("ks_ksvrp_images"))));
//        list.add(new KsSvsKsvrpDTO(2, IpfsClient.getIpfsUrl("" + redisTemplate.opsForList().leftPop("ks_ksvrp_images"))));
        list.add(new KsSvsKsvrpDTO(1,  "http://222.128.117.234:9003/imgs/zdjgsscl1.png"));
        list.add(new KsSvsKsvrpDTO(2,  "http://222.128.117.234:9003/imgs/zdjgsscl2.png"));
        return list;
    }
}
