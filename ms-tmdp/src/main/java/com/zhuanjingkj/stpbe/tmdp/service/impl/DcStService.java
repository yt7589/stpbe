package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DcStMapper;
import com.zhuanjingkj.stpbe.data.dto.DcStIlSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.DcStVMDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.*;
import com.zhuanjingkj.stpbe.data.dto.DcStVAreaDTO;
import com.zhuanjingkj.stpbe.data.dto.DcStVSiteDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDcStService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DcStService implements IDcStService {

    @Autowired
    private DcStMapper dcStMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private static Map<String, Object> siteMap = new HashMap<>();

    @Override
    public DcStSysDTO getSys_exp() {
        Integer device = dcStMapper.getDeviceCount(); //设备总数
        Integer control = dcStMapper.getVehicleControl(); //布控车辆
        Integer ils = dcStMapper.getIlCount(); //违章总数
        Integer alerts = Integer.parseInt(redisTemplate.opsForValue().get("dcst_vehicle_alerts") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_vehicle_alerts"));
        Integer keyVehicle = Integer.parseInt(redisTemplate.opsForValue().get("dcst_key_vehicle") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_key_vehicle") );
        Integer truck = Integer.parseInt(redisTemplate.opsForValue().get("dcst_key_truck") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_key_truck") );
        DcStSysDTO sysDTO = new DcStSysDTO(device,control,alerts,keyVehicle,ils,truck);
        return sysDTO;
    }

    @Override
    public DcStTodayDTO getStToday_exp() {
        Integer vehicle = Integer.parseInt(redisTemplate.opsForValue().get("dcst_vehicle_total") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_vehicle_total"));
        Integer vc = Integer.parseInt(redisTemplate.opsForValue().get("dcst_category_0_total") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_category_0_total"));
        Integer vt = Integer.parseInt(redisTemplate.opsForValue().get("dcst_category_1_total") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_category_1_total"));
        Double vcact = 100.0 * vc /vehicle;
        Integer device = dcStMapper.getDeviceCount(); //设备总数
        Integer dv = dcStMapper.getDvCount(); //异常设备
        Double dvOnline = 100.0 * dv / device;
        DcStTodayDTO dcStTodayDTO = new DcStTodayDTO(vehicle,vc,vt,vcact.intValue(),dvOnline.intValue());
        return dcStTodayDTO;
    }

    @Override
    public List<DcStVAreaDTO> getVarea_exp() {
//        List<DcStVAreaDTO> recs = new ArrayList<>();
//        recs.add(new DcStVAreaDTO(102,"西二旗",200000));
//        recs.add(new DcStVAreaDTO(103,"上地",300000));
//        recs.add(new DcStVAreaDTO(104,"西直门",400000));
//        recs.add(new DcStVAreaDTO(105,"知春路",500000));
//        recs.add(new DcStVAreaDTO(105,"北七家",600000));
        List<DcStVAreaDTO> recs = dcStMapper.getTop5Varea(); //过车量排行前5点位
        return recs;
    }

    @Override
    public List<DcStVSiteDTO> getVSite_exp() {
//        List<DcStVSiteDTO> recs = new ArrayList<>();
//        recs.add(new DcStVSiteDTO(107,"上地四街",116.0854321,40.823654,100000));
//        recs.add(new DcStVSiteDTO(107,"上地五街",116.0754321,40.723654,200000));
//        recs.add(new DcStVSiteDTO(107,"上地六街",116.0654321,40.623654,300000));
//        recs.add(new DcStVSiteDTO(107,"上地七街",116.0554321,40.523654,400000));
//        recs.add(new DcStVSiteDTO(107,"上地八街",116.0454321,40.423654,500000));
        List<DcStVSiteDTO> recs = dcStMapper.getTop5VSite();
        return recs;
    }

    @Override
    public List<DcStVTrendDTO> getVTr_exp() {
        List<DcStVTrendDTO> recs = new ArrayList<>();
        Map<String, Integer> res30Map = DateUtil.timeFor30Map(7); //生成7日过车集合
        String endTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String startTime = DateUtil.plusDaysForDate(endTime, -6);
        List<Map<String, Object>> res = dcStMapper.getWeekVehicle(endTime, startTime);
        if(res != null && res.size() > 0) {
            for(int i = 0; i < res.size(); i++) {
                res30Map.put("" + res.get(i).get("createTime"), Integer.parseInt("" + res.get(i).get("count")));
            }
        }
        for (String key : res30Map.keySet()) {
            recs.add(new DcStVTrendDTO(DateUtil.timeForMdStr(key),res30Map.get(key)));
        }
//        List<DcStVTrendDTO> recs = new ArrayList<>();
//        recs.add(new DcStVTrendDTO("12-20", 100000));
//        recs.add(new DcStVTrendDTO("12-21", 220000));
//        recs.add(new DcStVTrendDTO("12-22", 240000));
//        recs.add(new DcStVTrendDTO("12-23", 250000));
//        recs.add(new DcStVTrendDTO("12-24", 240000));
//        recs.add(new DcStVTrendDTO("12-25", 220000));
//        recs.add(new DcStVTrendDTO("12-26", 210000));
        return recs;
    }

    @Override
    public DcStVDTO getVst_exp() {
        DcStVDTO dcStVDTO = new DcStVDTO();
        String endTime = DateUtil.countDays(1); //7日统计结束时间
        String startTime = DateUtil.countDays(-6); //7日统计开始时间
        String month = DateUtil.getMonthOfYear(); //当前月份
        Integer today_st = dcStMapper.getTodaySt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Integer week_st = dcStMapper.getWeekSt(startTime, endTime);
        Integer month_st = dcStMapper.getMonthSt(month);
        dcStVDTO.setToday_st(today_st);
        dcStVDTO.setWeek_st(week_st);
        dcStVDTO.setMonth_st(month_st);
        List<DcStVMDTO> recs = new ArrayList<>();
        Map<String, Integer> res12Map = DateUtil.monthFor12Map();
        List<Map<String,Object>> dcstVmd = dcStMapper.getDcstVmd(LocalDate.now().getYear());
        if(dcstVmd != null && dcstVmd.size() > 0) {
            for(int i = 0; i < dcstVmd.size(); i++) {
                res12Map.put("" + dcstVmd.get(i).get("createTime"), Integer.parseInt("" + dcstVmd.get(i).get("count")));
            }
        }
        Integer mt = 1;
        for(String key : res12Map.keySet()) {
            recs.add(new DcStVMDTO(mt +"月",res12Map.get(key)));
            mt++;
        }
        dcStVDTO.setDcstVmDTO(recs);
        return dcStVDTO;
    }

    @Override
    public List<DcStIlSiteDTO> getIlSite_exp() {
        List<DcStIlSiteDTO> recs = dcStMapper.getDcstIlTop7Site();
//        List<DcStIlSiteDTO> recs = new ArrayList<>();
//        recs.add(new DcStIlSiteDTO(107,"西二旗",116.0454321,40.423654,111000));
//        recs.add(new DcStIlSiteDTO(108,"上地",116.0554321,40.443654,100000));
//        recs.add(new DcStIlSiteDTO(109,"西直门",116.0654321,40.463654,200000));
//        recs.add(new DcStIlSiteDTO(101,"知春路",116.0754321,40.483654,300000));
//        recs.add(new DcStIlSiteDTO(102,"东湖区",116.0854321,40.403654,400000));
//        recs.add(new DcStIlSiteDTO(103,"北七家",116.0954321,40.413654,150000));
//        recs.add(new DcStIlSiteDTO(104,"望京",116.1454321,40.553654,140000));
        return recs;
    }

    @Override
    public List<DcStKvSiteDTO> getDcKvs_exp() {
//        List<DcStKvSiteDTO> recs = new ArrayList<>();
//        recs.add(new DcStKvSiteDTO(107,"海淀区上龙泽",116.1454321,40.553654,140000));
//        recs.add(new DcStKvSiteDTO(108,"海淀区回龙观",116.2454321,40.653654,100000));
//        recs.add(new DcStKvSiteDTO(109,"昌平区北七家",116.3454321,40.753654,110000));
//        recs.add(new DcStKvSiteDTO(101,"海淀区西直门",116.4454321,40.853654,200000));
//        recs.add(new DcStKvSiteDTO(102,"朝阳区东湖区",116.5454321,40.953654,300000));
//        recs.add(new DcStKvSiteDTO(103,"海淀区上地",116.6454321,40.353654,200000));
//        recs.add(new DcStKvSiteDTO(104,"海淀区西二旗",116.7454321,40.573654,100000));
        List<DcStKvSiteDTO> recs = new ArrayList<>();
        String ym = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeWithScores("dcst_top7_site_" + ym,0,6);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTupleSet.iterator();
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple<Object>  typedTuple = iterator.next();
            recs.add(new DcStKvSiteDTO(0, "" + siteMap.get(typedTuple.getValue()),0,0,typedTuple.getScore().intValue()));
//            Object value = typedTuple.getValue();
//            double score1 = typedTuple.getScore();
//            System.out.println("通过reverseRangeWithScores(K key, long start, long end)方法索引倒序排列区间值:" + value + "----->" + score1);
        }
        return recs;
    }

    @Override
    public List<DcStTruckSiteDTO> getDcTruckSite_exp() {
        List<DcStTruckSiteDTO> recs = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeWithScores("dc_st_truck",0,6);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTupleSet.iterator();
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple<Object>  typedTuple = iterator.next();
            recs.add(new DcStTruckSiteDTO(0, "" + siteMap.get(typedTuple.getValue()),0,0,typedTuple.getScore().intValue()));
//            Object value = typedTuple.getValue();
//            double score1 = typedTuple.getScore();
//            System.out.println("通过reverseRangeWithScores(K key, long start, long end)方法索引倒序排列区间值:" + value + "----->" + score1);
        }
        return recs;
    }

    @PostConstruct
    public void getSiteInfo() {
        List<Map<String, Object>> recs = dcStMapper.getSiteInfo(); //{"code" : "site_name"}
        if(recs != null && recs.size() > 0) {
            for (int i = 0; i < recs.size(); i++) {
                siteMap.put("" + recs.get(i).get("camera_code"), recs.get(i).get("site_name"));
            }
        }
    }
}
