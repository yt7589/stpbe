package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.DkDctfItemDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTjrsItemDTO;
import com.zhuanjingkj.stpbe.tmdp.mapper.DkDctfMapper;
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
        List<DkDctfItemDTO> dklist = new ArrayList<>();
        for (String key : dctf.keySet()) {
            item = new DkDctfItemDTO(""+dctfMap.get(key), Integer.parseInt(dctf.get(key) == null ? "0" : "" + dctf.get(key)));
            dctfs.add(item);
        }
        /** 合并同一个路段下的camera拍照数量 */
        dctfs.parallelStream().collect(Collectors.groupingBy(o ->(o.getName()),Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a,b) -> new DkDctfItemDTO(a.getName(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                });
        /** 合并数据后排序*/
        Collections.sort(dklist, new Comparator<DkDctfItemDTO>() {
            @Override
            public int compare(DkDctfItemDTO o1, DkDctfItemDTO o2) {
                return new Double(o2.getCount()).compareTo(new Double(o1.getCount()));
            }
        });
        Integer rt = 0;
        if(dklist.size() < 10) {
            rt = dklist.size();
        } else {
            rt = 10;
        }
        /** 取出top10*/
        List<DkDctfItemDTO> list = dklist.subList(0, rt);
        return list;
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
