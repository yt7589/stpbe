package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DkDctfMapper;
import com.zhuanjingkj.stpbe.tmdp.dto.DkDctfItemDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTjrsItemDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkDctfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DkDctfService implements IDkDctfService {

    private static Map<String, Object> dctfMap = new HashMap<>();

    @Autowired
    private DkDctfMapper dkDctfMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<DkDctfItemDTO> getDkDctfItemDTOs_exp() {
        List<DkDctfItemDTO> dctfs = new ArrayList<>();
        DkDctfItemDTO item = null;
        Map<String, Object> dctf = redisTemplate.opsForHash().entries("dk_dctf_area");
        if (dctf != null && dctf.size() > 0) {
           for (String key : dctf.keySet()) {
               dctfs.add(new DkDctfItemDTO("" + dctfMap.get(key), Integer.parseInt(dctf.get(key) == null ? "0" : "" + dctf.get(key))));
           }
        }
        /** 合并同一个路段下的camera拍照数量 */
        List<DkDctfItemDTO> dklist = new ArrayList<>();
        dctfs.parallelStream().collect(Collectors.groupingBy(o ->(o.getName()),Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a,b) -> new DkDctfItemDTO(a.getName(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                });
        /**
         * 区县过车数量初始化
         */
        StringBuffer sb = new StringBuffer();
        if (dklist == null) {
            for (String key : dctfMap.keySet()) {
                if (sb.toString().contains(dctf.get(key) + "")) {
                    continue;
                } else {
                    item = new DkDctfItemDTO(""+dctfMap.get(key), 0);
                    dctfs.add(item);
                    sb.append(dctfMap.get(key));
                }
                if (dctfs.size() == 16) {
                    break;
                }
            }
        } else if (dklist != null && dklist.size() < 16) {
            for (int i = 0; i < dklist.size(); i++) {
                sb.append(dklist.get(i).getName());
            }
            for (String key : dctfMap.keySet()) {
                if (sb.toString().contains(dctfMap.get(key) +"")) {
                    continue;
                } else {
                    item = new DkDctfItemDTO("" + dctfMap.get(key), 0);
                    dklist.add(item);
                    sb.append(dctfMap.get(key));
                }
                if (dklist.size() == 10) {
                    break;
                }
            }
        }

        return dklist;
    }

    @PostConstruct
    public void init() {
        List<Map> area = dkDctfMapper.getArea();
        if(area != null && area.size() > 0) {
            for (int i= 0; i < area.size(); i++) {
                dctfMap.put("" + area.get(i).get("camera_code"), area.get(i).get("area_name"));
            }
        }
    }
}
