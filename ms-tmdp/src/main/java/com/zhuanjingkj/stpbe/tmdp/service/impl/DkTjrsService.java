package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DkTjrsMapper;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTjrsItemDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkTjrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DkTjrsService implements IDkTjrsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DkTjrsMapper dkTjrsMapper;

    private static Map<String, Object> tjrsMap = new HashMap<>();

    @Override
    public List<DkTjrsItemDTO> getDkTjrsItemDTOs_exp() {
        List<DkTjrsItemDTO> items = new ArrayList<>();
        DkTjrsItemDTO item = null;
        Map<String, Object> tjrs = redisTemplate.opsForHash().entries("dk_tjrs_road");
        List<DkTjrsItemDTO> dklist = new ArrayList<>();
        if (tjrs != null && tjrs.size() > 0) {
            for (String key : tjrs.keySet()) {
                items.add(new DkTjrsItemDTO("" + tjrsMap.get(key), Integer.parseInt(tjrs.get(key) == null ? "0" : "" + tjrs.get(key))));
            }
        }
        /** 合并同一个路段下的camera拍照数量 */
        items.parallelStream().collect(Collectors.groupingBy(o ->(o.getName()),Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a,b) -> new DkTjrsItemDTO(a.getName(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                });
        /** 合并数据后排序*/
        Collections.sort(dklist, new Comparator<DkTjrsItemDTO>() {
            @Override
            public int compare(DkTjrsItemDTO o1, DkTjrsItemDTO o2) {
                return new Double(o2.getCount()).compareTo(new Double(o1.getCount()));
            }
        });
        /***
         * 初始化拥堵路段数据
         */
        StringBuffer sb = new StringBuffer();
        if (dklist == null) {
            for (String key : tjrsMap.keySet()) {
                if (sb.toString().contains(tjrsMap.get(key) +"")) {
                    continue;
                } else {
                    item = new DkTjrsItemDTO(""+tjrsMap.get(key), 0);
                    dklist.add(item);
                    sb.append(tjrsMap.get(key));
                }
                if (items.size() == 10) {
                    break;
                }
            }
        } else if (dklist != null && dklist.size() < 10) {
            for (int i = 0; i < dklist.size(); i++) {
                sb.append(dklist.get(i).getName());
            }
            for (String key : tjrsMap.keySet()) {
                if (sb.toString().contains(tjrsMap.get(key) +"")) {
                    continue;
                } else {
                    item = new DkTjrsItemDTO(""+tjrsMap.get(key), 0);
                    dklist.add(item);
                    sb.append(tjrsMap.get(key));
                }
                if (dklist.size() == 10) {
                    break;
                }
            }
        }
        /********/
        Integer rt = 0;
        if(dklist.size() < 10) {
            rt = dklist.size();
        } else {
            rt = 10;
        }
        /** 取出top10*/
        List<DkTjrsItemDTO> list = dklist.subList(0, rt);
        return list;
    }

    @PostConstruct
    public void init() {
        List<Map> road = dkTjrsMapper.getRoad();
        if(road != null && road.size() > 0) {
            for (int i= 0; i < road.size(); i++) {
                tjrsMap.put("" + road.get(i).get("camera_code"), road.get(i).get("road_name"));
            }
        }
    }

}
