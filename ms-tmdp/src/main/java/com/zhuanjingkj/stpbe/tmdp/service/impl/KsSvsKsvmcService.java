package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsSvsKsvmcsMapper;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvmcDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsKsvmcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KsSvsKsvmcService implements IKsSvsKsvmcService {

    @Autowired
    private KsSvsKsvmcsMapper ksSvsKsvmcsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<KsSvsKsvmcDTO> getKsSvsKsvmcDTOs_exp() {
        List<KsSvsKsvmcDTO> ksvmcs = new ArrayList<>();
        List<Map<String, Object>> vt = ksSvsKsvmcsMapper.getVehicleType();
        if(vt != null && vt.size() >0) {
            for (int i = 0; i < vt.size(); i++) {
                if(redisTemplate.hasKey(vt.get(i).get("vehicle_type_code"))) {
                    ksvmcs.add(new KsSvsKsvmcDTO("" + vt.get(i).get("vehicle_type_name"),
                            Integer.parseInt("" +redisTemplate.opsForValue().get(vt.get(i).get("vehicle_type_code")))));
                }
            }
        }
        return ksvmcs;
    }
}
