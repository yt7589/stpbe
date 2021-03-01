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
        List<KsLpsAreaDTO> lpsAreas = new ArrayList<>();
        Map<String, Object> lpsMap = redisTemplate.opsForHash().entries("ks_lps_area");
        StringBuffer sb = new StringBuffer();
        Map<String, Object> areaMap = KsAsService.areaMap;
        for (String key : lpsMap.keySet()) {
            lpsAreas.add(new KsLpsAreaDTO("" + areaMap.get(key), Integer.parseInt("" +lpsMap.get(key))));
            sb.append(areaMap.get(key));
        }
        List<KsLpsAreaDTO> dklist = new ArrayList<>();
        /** 合并同一个区域内的camera拍照数量 */
        lpsAreas.parallelStream().collect(Collectors.groupingBy(o ->(o.getName()),Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a,b) -> new KsLpsAreaDTO(a.getName(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                });
        if (dklist != null && dklist.size() > 0) {
            for (String key : areaMap.keySet()) {
                if (!sb.toString().contains(areaMap.get(key) + "")) {
                    dklist.add(new KsLpsAreaDTO("" + areaMap.get(key), 0));
                }
                if (dklist.size() == 10) {
                    break;
                }
            }
        }
        return dklist;
    }

    @Override
    public List<KsLpsSiteDTO> getSiteAbnormalLicensePlate() {
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
