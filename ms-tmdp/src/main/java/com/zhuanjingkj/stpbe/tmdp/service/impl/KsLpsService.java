package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.mapper.KsLpsMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsLpsAreaDTO;
import com.zhuanjingkj.stpbe.data.dto.KsLpsSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.KsLpsLalpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsLpsTimeDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsLpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KsLpsService implements IKsLpsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KsLpsMapper ksLpsMapper;

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Override
    public List<KsLpsTimeDTO> getTimeAbnormalLicensePlate() {
        List<KsLpsTimeDTO> spsTimes = new ArrayList<>();
        for (int i = 1; i < 25; i++ ) {
            spsTimes.add(new KsLpsTimeDTO( "" + (i < 10 ? ("0" + i) : i) +":00", Integer.parseInt("" + redisTemplate.opsForList().index("ks_lps_time", i - 1))));
        }
        return spsTimes;
    }

    @Override
    public List<KsLpsAreaDTO> getAreaAbnormalLicensePlate() {
//        String[] areas = {"东城区","西城区","朝阳区","丰台区","石景山区","海淀区","顺义区","通州区","大兴区","房山区","门头沟区","昌平区","平谷区","密云区","怀柔区","延庆区"};
        List<KsLpsAreaDTO> lpsAreas = new ArrayList<>();
//        for (int i = 0; i < areas.length; i++ ) {
//            lpsAreas.add(new KsLpsAreaDTO(areas[i], i * 10000));
//        }
        Map<String, Object> lpsMap = redisTemplate.opsForHash().entries("ks_lps_area");
        for (String key : lpsMap.keySet()) {
            lpsAreas.add(new KsLpsAreaDTO("" + KsAsService.areaMap.get(key), Integer.parseInt("" +lpsMap.get(key))));
        }

        List<KsLpsAreaDTO> dklist = new ArrayList<>();
        /** 合并同一个区域内的camera拍照数量 */
        lpsAreas.parallelStream().collect(Collectors.groupingBy(o ->(o.getName()),Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a,b) -> new KsLpsAreaDTO(a.getName(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                });
        return dklist;
    }

    @Override
    public List<KsLpsSiteDTO> getSiteAbnormalLicensePlate() {
//        List<KsLpsSiteDTO> lpsSite = new ArrayList<>();
//        for (int i = 1; i < 3; i++ ) {
//            lpsSite.add(new KsLpsSiteDTO(i + 1, "上地" + i + "街",("豫E" + (2222 + i)),(30 + i),(116.3954 + i * 001),(40.082 + i * 0.02)));
//        }
        List<KsLpsSiteDTO> lpsSite =  ksLpsMapper.getLpsSite();
        if(lpsSite != null && lpsSite.size() > 0) {
            for (int i = 0; i < lpsSite.size(); i++) {
                Integer count = ksLpsMapper.getLpsSiteCount(lpsSite.get(i).getSiteId(), lpsSite.get(i).getHphm());
                lpsSite.get(i).setTotalTimes(count);
            }
        }
        return lpsSite;
    }

    @Override
    public List<KsLpsLalpDTO> getKsLpsLalp() {
//        List<KsLpsLalpDTO> lpsSite = new ArrayList<>();
//        for (int i = 1; i < 10; i++ ) {
//            lpsSite.add(new KsLpsLalpDTO((28 + i),(56 + i),(100 + i), ("上地" + i +"街"),"2020-12-25 14:40:13",("豫E" + (2222 + i)),(30 + i),(25 + i),"http://222.128.117.234:9003/imgs/pzyc.png"));
//        }
        List<KsLpsLalpDTO> lpsSite = ksLpsMapper.getLpsLalp();
        if(lpsSite != null && lpsSite.size() >0) {
            for (int i = 0; i < lpsSite.size(); i++ ) {
                long jsonId = lpsSite.get(i).getTvisJsonId();
                String tblName = lpsSite.get(i).getTvisJsonTbl().replace("StpDb.", "");
                Map<String, Object> map = dkRtvrMapper.getImageHash(jsonId, tblName);
                if(map != null && map.size() > 0) {
                    lpsSite.get(i).setImageUrl(IpfsClient.getIpfsUrl("" + map.get("image_hash")));
                    lpsSite.get(i).setOccurTime("" + map.get("occur_time"));
                }
            }
        }
        return lpsSite;
    }


}
