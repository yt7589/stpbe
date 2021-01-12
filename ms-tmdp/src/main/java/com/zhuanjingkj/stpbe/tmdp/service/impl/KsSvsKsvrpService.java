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
        list.add(new KsSvsKsvrpDTO(1,  "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
        list.add(new KsSvsKsvrpDTO(2,  "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
        return list;
    }
}
