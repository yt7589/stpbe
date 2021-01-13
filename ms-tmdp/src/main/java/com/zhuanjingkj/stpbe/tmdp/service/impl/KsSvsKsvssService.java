package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsSvsKsvssMapper;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTjrsItemDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvadDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvssDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsKsvssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KsSvsKsvssService implements IKsSvsKsvssService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KsSvsKsvssMapper ksSvsKsvssMapper;

    @Override
    public List<KsSvsKsvssDTO> getKsSvsKsvssDTOs_exp() {
        List<KsSvsKsvssDTO> ksvsss = new ArrayList<>();
//        ksvsss.add(new KsSvsKsvssDTO(101, "上地三街", 1231));
//        ksvsss.add(new KsSvsKsvssDTO(102, "西三旗", 2345));
//        ksvsss.add(new KsSvsKsvssDTO(103,"西二旗", 1102));
//        ksvsss.add(new KsSvsKsvssDTO(104, "王道口", 12345));
//        ksvsss.add(new KsSvsKsvssDTO(105, "西直门", 19321));
//        ksvsss.add(new KsSvsKsvssDTO(106, "六里桥", 15335));
//        ksvsss.add(new KsSvsKsvssDTO(107, "王府井", 18221));
        Map<String, Object> dctf = redisTemplate.opsForHash().entries("ks_ksvrp_site"); //取出cameraId 和 对应的 count
        /**
         * 1.根据cameraId 取出点位名称
         * 2.
         */
        List<String> camera = new ArrayList<>();
        for(String key : dctf.keySet()) {
            camera.add(key);
        }
        List<Map<String, Object>> ksvss = ksSvsKsvssMapper.getKsvss(camera);
        KsSvsKsvssDTO ksSvsKsvssDTO = null;
        if(ksvss != null && ksvss.size() > 0){
            for(int i = 0; i < ksvss.size(); i++) {
                for(String key : dctf.keySet()) {
                    if(ksvss.get(i).get("camera_code").equals(key)) {
                        ksSvsKsvssDTO = new KsSvsKsvssDTO(Integer.parseInt("" + ksvss.get(i).get("site_id")), "" + ksvss.get(i).get("site_name"), Integer.parseInt("" + dctf.get(key)));
                        ksvsss.add(ksSvsKsvssDTO);
                    }
                }
            }
        }
        /** 合并同一个路段下的camera拍照数量 */
        List<KsSvsKsvssDTO> dklist = new ArrayList<>();
        ksvsss.parallelStream().collect(Collectors.groupingBy(o ->(o.getSiteName()),Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a,b) -> new KsSvsKsvssDTO(a.getSiteId(), a.getSiteName(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                });
        return dklist;
    }
}
