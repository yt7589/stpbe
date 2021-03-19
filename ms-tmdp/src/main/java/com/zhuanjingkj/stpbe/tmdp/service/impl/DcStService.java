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

    public static Map<String, Object> siteNameMap = new HashMap<>();

    public static Map<String, Object> siteMap = new HashMap<>();

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
        Double dvOnline = (device - dv) / device * 100.0;
        DcStTodayDTO dcStTodayDTO = new DcStTodayDTO(vehicle,vc,vt,vcact.intValue(),dvOnline.intValue());
        return dcStTodayDTO;
    }

    @Override
    public List<DcStVAreaDTO> getVarea_exp() {
        List<DcStVAreaDTO> recs = dcStMapper.getTop5Varea(); //过车量排行前5点位
        return recs;
    }

    @Override
    public List<DcStVSiteDTO> getVSite_exp() {
        List<DcStVSiteDTO> recs = dcStMapper.getTop5VSite();
        return recs;
    }

    @Override
    public List<DcStVTrendDTO> getVTr_exp() {
        List<DcStVTrendDTO> recs = new ArrayList<>();
        Map<String, Integer> res30Map = DateUtil.dayFor30Map(7, DateUtil.DTF_NYR); //生成7日过车集合
        String endTime = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
        List<DcStKvSiteDTO> recs = new ArrayList<>();
        String ym = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeWithScores("dcst_top7_site_" + ym,0,6);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTupleSet.iterator();
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple<Object>  typedTuple = iterator.next();
            recs.add(new DcStKvSiteDTO(0, "" + siteNameMap.get(typedTuple.getValue()),0,0,typedTuple.getScore().intValue()));
        }
        StringBuffer sb = new StringBuffer();
        if (recs == null) {
            for (String key : siteNameMap.keySet()) {
                if (!sb.toString().contains(siteNameMap.get(key) +"")) {
                    recs.add(new DcStKvSiteDTO(0, "" + siteNameMap.get(key),0,0,0));
                    sb.append(siteNameMap.get(key));
                }
                if (recs.size() == 10) {
                    break;
                }
            }
        } else if (recs != null && recs.size() < 10) {
            if (recs.size() > 0) {
                for (int i = 0; i < recs.size(); i++) {
                    sb.append(recs.get(i).getSiteName());
                }
            }
            for (String key : siteNameMap.keySet()) {
                if (!sb.toString().contains(siteNameMap.get(key) +"")) {
                    recs.add(new DcStKvSiteDTO(0, "" + siteNameMap.get(key),0,0,0));
                    sb.append(siteNameMap.get(key));
                }
                if (recs.size() == 10) {
                    break;
                }
            }
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
            recs.add(new DcStTruckSiteDTO(0, "" + siteNameMap.get(typedTuple.getValue()),0,0,typedTuple.getScore().intValue()));
        }
        StringBuffer sb = new StringBuffer();
        if (recs == null) {
            for (String key : siteNameMap.keySet()) {
                if (!sb.toString().contains(siteNameMap.get(key) +"")) {
                    recs.add(new DcStTruckSiteDTO(0, "" + siteNameMap.get(key),0,0,0));
                    sb.append(siteNameMap.get(key));
                }
                if (recs.size() == 10) {
                    break;
                }
            }
        } else if (recs != null && recs.size() < 10) {
            if (recs.size() > 0) {
                for (int i = 0; i < recs.size(); i++) {
                    sb.append(recs.get(i).getSiteName());
                }
            }
            for (String key : siteNameMap.keySet()) {
                if (!sb.toString().contains(siteNameMap.get(key) +"")) {
                    recs.add(new DcStTruckSiteDTO(0, "" + siteNameMap.get(key),0,0,0));
                    sb.append(siteNameMap.get(key));
                }
                if (recs.size() == 10) {
                    break;
                }
            }
        }
        return recs;
    }

    @PostConstruct
    public void getSiteInfos() {
        List<Map<String, Object>> recs = dcStMapper.getSiteInfo(); //{"code" : "site_name"}
        if(recs != null && recs.size() > 0) {
            for (int i = 0; i < recs.size(); i++) {
                siteNameMap.put("" + recs.get(i).get("camera_code"), recs.get(i).get("site_name"));
                siteMap.put("" + recs.get(i).get("camera_code"), recs.get(i).get("coordinate"));
            }
        }
    }
}
