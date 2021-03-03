package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.data.dto.TnVsVehicleDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkDctfItemDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsTopSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVsTopVehicleDTO;
import com.zhuanjingkj.stpbe.tmdp.service.ITnVsService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TnVsService implements ITnVsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DcStService dcStService;

    @Override
    public List<TnVsTopSiteDTO> getTvtsDTO_exp() {
        List<TnVsTopSiteDTO> tvts = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeWithScores("tn_vs_site_vehicle",0,9);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTupleSet.iterator();
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple<Object>  typedTuple = iterator.next();
            tvts.add(new TnVsTopSiteDTO("" + dcStService.siteNameMap.get("" + typedTuple.getValue()),
                    Double.parseDouble(("" + dcStService.siteMap.get("" + typedTuple.getValue())).split("\\|")[0] == null ? "0" : ("" + dcStService.siteMap.get("" + typedTuple.getValue())).split("\\|")[0]),
                    Double.parseDouble(("" + dcStService.siteMap.get("" + typedTuple.getValue())).split("\\|")[1] == null ? "0" : ("" + dcStService.siteMap.get("" + typedTuple.getValue())).split("\\|")[1]),
                    typedTuple.getScore().intValue()));
        }
        /** 合并同一个路段下的camera拍照数量 */
        List<TnVsTopSiteDTO> dklist = new ArrayList<>();
        tvts.parallelStream().collect(Collectors.groupingBy(o ->(o.getName()),Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a,b) -> new TnVsTopSiteDTO(a.getName(), a.getLng(), b.getLat(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                });
        /**
         * 如果点位不足10个要补充
         */
        StringBuffer sb = new StringBuffer();
        Map<String, Object> siteNameMap = DcStService.siteNameMap;
        if (dklist == null || dklist.size() == 0) {
            for (String key : siteNameMap.keySet()) {
                dklist.add(new TnVsTopSiteDTO("" + siteNameMap.get(key),0.0,0.0,0));
                if (dklist.size() == 10) {
                    break;
                }
            }
        } else if (dklist != null && dklist.size() < 10) {
            for (int i = 0; i < dklist.size(); i++) {
                sb.append(dklist.get(i).getName());
            }
            for (String key : siteNameMap.keySet()) {
                if (!sb.toString().contains("" + siteNameMap.get(key))) {
                    dklist.add(new TnVsTopSiteDTO("" + siteNameMap.get(key),0.0,0.0,0));
                    if (dklist.size() == 10) {
                        break;
                    }
                }
            }
        }
        return dklist;
    }

    @Override
    public TnVsVehicleDTO getTvtvdDTO_exp() {
        TnVsVehicleDTO tv = new TnVsVehicleDTO();
        List<TnVsTopVehicleDTO> tvtv = null;
        String today = DateUtil.plusDays(0);
        String yesterday = DateUtil.plusDays(-1);
        List<Integer> tsfvs = redisTemplate.opsForList().range("tn_vs_trend_" + today, 0, 23);
        List<Integer> ysfvs = redisTemplate.opsForList().range("tn_vs_trend_" + yesterday, 0, 23);
        tvtv = new ArrayList<>();
        if(tsfvs != null && tsfvs.size() > 0) {
            for(int i = 0; i < tsfvs.size(); i++) {
                tvtv.add(new TnVsTopVehicleDTO("" + (i+1), tsfvs.get(i)));
            }
            tv.setTsfvs(tvtv);
        } else {
            for(int i = 0; i < 24; i++) {
                tvtv.add(new TnVsTopVehicleDTO("" + (i+1), 0));
            }
        }
        tv.setTsfvs(tvtv);
        tvtv = new ArrayList<>();
        if(ysfvs != null && ysfvs.size() > 0) {
            for(int i = 0; i < ysfvs.size(); i++) {
                tvtv.add(new TnVsTopVehicleDTO("" + (i+1), ysfvs.get(i)));
            }
        } else {
            for(int i = 0; i < 24; i++) {
                tvtv.add(new TnVsTopVehicleDTO("" + (i+1), 0));
            }
        }
        tv.setYsfvs(tvtv);
        return tv;
    }
}
