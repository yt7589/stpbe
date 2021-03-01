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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
            System.out.println(typedTuple.getValue());
            tvts.add(new TnVsTopSiteDTO("" + dcStService.siteNameMap.get("" + typedTuple.getValue()),
                    Double.parseDouble(("" + dcStService.siteMap.get("" + typedTuple.getValue())).split("\\|")[0] == null ? "0" : ("" + dcStService.siteMap.get("" + typedTuple.getValue())).split("\\|")[0]),
                    Double.parseDouble(("" + dcStService.siteMap.get("" + typedTuple.getValue())).split("\\|")[1] == null ? "0" : ("" + dcStService.siteMap.get("" + typedTuple.getValue())).split("\\|")[1]),
                    typedTuple.getScore().intValue()));
        }
//        tvts.add(new TnVsTopSiteDTO("海淀区西二旗", 311000020));
//        tvts.add(new TnVsTopSiteDTO("海淀区上地", 1200000000));
//        tvts.add(new TnVsTopSiteDTO("海淀区西直门", 2120000000));
//        tvts.add(new TnVsTopSiteDTO("海淀区知春路", 310000002));
//        tvts.add(new TnVsTopSiteDTO("朝阳区东湖渠", 410000002));
//        tvts.add(new TnVsTopSiteDTO("昌平区北七家", 510000002));
//        tvts.add(new TnVsTopSiteDTO("海淀区回龙观", 611200002));
//        tvts.add(new TnVsTopSiteDTO("海淀区龙泽", 722658240));
//        tvts.add(new TnVsTopSiteDTO("海淀区魏公村", 813123300));
//        tvts.add(new TnVsTopSiteDTO("海淀区大钟寺", 322100110));
        /** 合并同一个路段下的camera拍照数量 */
        List<TnVsTopSiteDTO> dklist = new ArrayList<>();
        tvts.parallelStream().collect(Collectors.groupingBy(o ->(o.getName()),Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a,b) -> new TnVsTopSiteDTO(a.getName(), a.getLng(), b.getLat(), a.getCount() + b.getCount())).ifPresent(dklist :: add);
                });
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
//    @Override
//    public List<TnVsSiteDTO> getTvsdDTO_exp() {
//        List<TnVsSiteDTO> tvsd = new ArrayList<>();
//        tvsd.add(new TnVsSiteDTO(105,"上地街道78号",116.1987,40.9365));
//        tvsd.add(new TnVsSiteDTO(105,"上地街道78号",116.2987,40.8365));
//        tvsd.add(new TnVsSiteDTO(105,"上地街道178号",116.3987,40.7365));
//        tvsd.add(new TnVsSiteDTO(105,"上地街道728号",116.4987,40.6365));
//        tvsd.add(new TnVsSiteDTO(105,"上地街道278号",116.5987,40.5365));
//        return tvsd;
//    }
}
