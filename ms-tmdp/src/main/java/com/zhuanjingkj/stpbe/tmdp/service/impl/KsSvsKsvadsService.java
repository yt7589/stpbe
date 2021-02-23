package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsSvsHtfsMapper;
import com.zhuanjingkj.stpbe.common.mapper.KsSvsKsvadsMapper;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTjrsItemDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvadDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsKsvadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KsSvsKsvadsService implements IKsSvsKsvadsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KsSvsKsvadsMapper ksSvsKsvadsMapper;

    @Override
    public List<KsSvsKsvadDTO> getKsSvsKsvadDTOs_exp() {
        List<KsSvsKsvadDTO> list = new ArrayList<>();
        List<Map<String, Object>> kc = ksSvsKsvadsMapper.getKaCameraCode();
        KsSvsKsvadDTO ksSvsKsvadDTO = null;
        if(kc != null && kc.size() > 0) {
            for(int i = 0; i < kc.size(); i++) {
                if(redisTemplate.opsForHash().get("ks_svs_area", kc.get(i).get("camera_code")) != null) {
                    Integer count = (int)redisTemplate.opsForHash().get("ks_svs_area", kc.get(i).get("camera_code"));
                    ksSvsKsvadDTO = new KsSvsKsvadDTO("" + kc.get(i).get("area_name"),count);
                    list.add(ksSvsKsvadDTO);
                }
            }
        }
        List<KsSvsKsvadDTO> dklist = new ArrayList<>();
        /** 合并同一个路段下的camera拍照数量 */
        list.parallelStream().collect(Collectors.groupingBy(o ->(o.getName()),Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a,b) -> new KsSvsKsvadDTO(a.getName(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                });
        return dklist;
    }
}
