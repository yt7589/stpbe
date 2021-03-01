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

    @Autowired
    private DcStService dcStService;

    @Override
    public List<KsSvsKsvssDTO> getKsSvsKsvssDTOs_exp() {
        List<KsSvsKsvssDTO> ksvsss = new ArrayList<>();
        Map<String, Object> dctf = redisTemplate.opsForHash().entries("ks_ksvrp_site"); //取出cameraId 和 对应的 count
        /**
         * 1.根据cameraId 取出点位名称
         */
        List<KsSvsKsvssDTO> dklist = new ArrayList<>();
        if (dctf != null) {
            List<String> camera = new ArrayList<>();
            for (String key : dctf.keySet()) {
                camera.add(key);
            }
            KsSvsKsvssDTO ksSvsKsvssDTO = null;
            List<Map<String, Object>> ksvss = ksSvsKsvssMapper.getKsvss(camera);
            StringBuffer sb = new StringBuffer();
            Map<String, Object> siteMap = dcStService.siteNameMap;
            if (ksvss != null && ksvss.size() > 0){
                for (int i = 0; i < ksvss.size(); i++) {
                    for(String key : dctf.keySet()) {
                        sb.append(ksvss.get(i).get("site_name"));
                        if (ksvss.get(i).get("camera_code").equals(key)) {
                            ksSvsKsvssDTO = new KsSvsKsvssDTO(Integer.parseInt("" + ksvss.get(i).get("site_id")), "" + ksvss.get(i).get("site_name"), Integer.parseInt("" + dctf.get(key)));
                            ksvsss.add(ksSvsKsvssDTO);
                        }
                    }
                }
                if (ksvsss.size() < 10) {
                    for (String key : siteMap.keySet()) {
                        if (!sb.toString().contains("" + siteMap.get(key))) {
                            ksvsss.add(new KsSvsKsvssDTO(0, "" + siteMap.get(key), 0));
                            sb.append(siteMap.get(key));
                        }
                        if (ksvsss.size() == 10) {
                            break;
                        }
                    }
                }
            } else {
                for (String key : siteMap.keySet()) {
                    if (!sb.toString().contains("" + siteMap.get(key))) {
                        ksvsss.add(new KsSvsKsvssDTO(0, "" + siteMap.get(key), 0));
                        sb.append(siteMap.get(key));
                    }
                    if (ksvsss.size() == 10) {
                        break;
                    }
                }
            }
            /** 合并同一个路段下的camera拍照数量 */
            ksvsss.parallelStream().collect(Collectors.groupingBy(o ->(o.getSiteName()),Collectors.toList())).forEach(
                    (id, transfer) -> {
                        transfer.stream().reduce((a,b) -> new KsSvsKsvssDTO(a.getSiteId(), a.getSiteName(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                    });
        }
        return dklist;
    }
}
