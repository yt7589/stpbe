package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DcRtMapper;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcRtAreaJamDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcRtAreaVehicleDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcRtTimeJamDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcRtTimeVehicleDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDcRtService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

@Service
public class DcRtService implements IDcRtService {

    @Autowired
    private DcRtMapper dcRtMapper;

    @Override
    public List<DcRtTimeJamDTO> getRtj_exp(String tp) {
        List<DcRtTimeJamDTO> recs = new ArrayList<>();
        String endTime = DateUtil.countDays(0); //当前时间 2020-02-02
        String pEndTime = DateUtil.countDays(1); //结束时间
        Integer total;
        if("week".equals(tp)) {  //近7天
            total = 0;
            String startTime = DateUtil.plusDays(endTime, -6, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcForWeek(startTime, pEndTime);
            Map<String, Integer> rtjForWeek = DateUtil.dayFor30Map(6, DateUtil.DTF_MD);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjForWeek.put("" + rtj.get(i).get("rj"), count);
                    total += count;
                }
            }
            for(String key : rtjForWeek.keySet()) {
                recs.add(new DcRtTimeJamDTO(key, str2Double((double)rtjForWeek.get(key)/total)));
            }
        } else if("month".equals(tp)) { //近30天
            total = 0;
            String startTime = DateUtil.plusDays(endTime, -29, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcForWeek(startTime, pEndTime);
            Map<String, Integer> rtjFor4W = DateUtil.dayFor30Map(-29, DateUtil.DTF_MD);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i <rtj.size(); i++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjFor4W.put("" + rtj.get(i).get("rj"), count);
                    total += count;
                }
            }
            Integer day = 1;
            for(String key : rtjFor4W.keySet()) {
                recs.add(new DcRtTimeJamDTO("" + day, str2Double((double)rtjFor4W.get(key)/total)));
                day++;
            }
        } else if("quarter".equals(tp)) { //近90天
            total = 0;
            String startTime = DateUtil.plusDays(endTime, -89, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcFor3m(startTime, pEndTime);
            Map<String, Integer> rtjFor3m = DateUtil.monthFor3Map(4, false);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjFor3m.put("" + rtj.get(i).get("rj"), count);
                    total += count;
                }
            }
            for(String key : rtjFor3m.keySet()) {
                if(rtjFor3m.get(key) > 0) {
                    recs.add(new DcRtTimeJamDTO(key, str2Double((double)rtjFor3m.get(key)/total)));
                }
            }
        } else if("half" .equals(tp)) { //近半年
            total = 0;
            String startTime = DateUtil.plusDays(endTime, -180, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcFor3m(startTime, pEndTime);
            Map<String, Integer> rtjFor3m = DateUtil.monthFor3Map(7, false);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjFor3m.put("" + rtj.get(i).get("rj"), count);
                    total += count;
                }
            }
            for(String key : rtjFor3m.keySet()) {
                if(rtjFor3m.get(key) > 0) {
                    recs.add(new DcRtTimeJamDTO(key, str2Double((double)rtjFor3m.get(key)/total)));
                }
            }
        } else if("year".equals(tp)) { //近一年
            total = 0;
            String startTime = DateUtil.plusDays(endTime, -365, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcFor3m(startTime, pEndTime);
            Map<String, Integer> rtjFor3m = DateUtil.monthFor3Map(12, false);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i++) {
                    Integer count =  Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjFor3m.put("" + rtj.get(i).get("rj"), count);
                    total += count;
                }
            }
            for(String key : rtjFor3m.keySet()) {
                if(rtjFor3m.get(key) > 0) {
                    recs.add(new DcRtTimeJamDTO(key, str2Double((double)rtjFor3m.get(key)/total)));
                }
            }
        } else {
            total = 0;
            List<Map<String, Object>> rtj = dcRtMapper.getDcForDay(endTime, pEndTime);
            Map<String, Integer> rtjFor24Map = DateUtil.timeFor24Map();
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i ++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("rj") == null ? "0" : "" + rtj.get(i).get("rj"));
                    rtjFor24Map.put("" + rtj.get(i).get("rj"), count);
                    total += count;
                }
            }
            for(String key : rtjFor24Map.keySet()) {
                recs.add(new DcRtTimeJamDTO(key +":00", str2Double((double)rtjFor24Map.get(key)/total)));
            }
        }
        return recs;
    }

    @Override
    public List<DcRtAreaJamDTO> getRaj_exp(String tp) {
        List<DcRtAreaJamDTO> recs = new ArrayList<>();
        String endTime = DateUtil.countDays(1);
        List<Map<String, Object>> raj = new ArrayList<>();
        if("week".equals(tp)) { //一周
            String startTime = DateUtil.countDays(-6);
            raj = dcRtMapper.getRajForDay(startTime, endTime);
        } else if("month".equals(tp)) { //一月
            String startTime = DateUtil.countDays(-29);
            raj = dcRtMapper.getRajForDay(startTime, endTime);
        } else if("quarter".equals(tp)) { //三月
            String startTime = DateUtil.countDays(-89);
            raj = dcRtMapper.getRajForDay(startTime, endTime);
        } else if("half".equals(tp)) { //半年
            String startTime = DateUtil.countDays(-180);
            raj = dcRtMapper.getRajForDay(startTime, endTime);
        } else if("year".equals(tp)) { //一年
            String startTime = DateUtil.countDays(-365);
            raj = dcRtMapper.getRajForDay(startTime, endTime);
        } else { //当天
            String startTime = DateUtil.countDays(0);
            raj = dcRtMapper.getRajForDay(startTime, endTime);
        }
        Integer total = 0;
        if(raj != null && raj.size() > 0) {
            for(int i = 0; i < raj.size(); i++) {
                total += Integer.parseInt(raj.get(i).get("count") +"");
            }
            for(int i = 0; i < raj.size(); i++) {
                Integer count = Integer.parseInt(raj.get(i).get("count") +"");
                recs.add(new DcRtAreaJamDTO(0,"" + raj.get(i).get("area_name"),Double.parseDouble("" +raj.get(i).get("lng")),
                        Double.parseDouble("" +raj.get(i).get("lat")), str2Double((double) count/total),str2Double((double) count/total)));
            }
        }
        return recs;
    }

    @Override
    public List<DcRtTimeVehicleDTO> getRtv_exp(String tp) {
        List<DcRtTimeVehicleDTO> recs = new ArrayList<>();
        String endTime = DateUtil.countDays(1);
        List<Map<String, Object>> rtv = new ArrayList<>();
        if("week".equals(tp)) { //一周
            String startTime = DateUtil.countDays(-6);
            rtv = dcRtMapper.getDcForDay(startTime, endTime);
            Map<String, Integer> rtvMap = DateUtil.dayFor30Map(6, DateUtil.DTF_MD);
            if(rtv != null && rtv.size() > 0) {
                for(int i =0; i < rtv.size(); i++) {
                    rtvMap.put("" + rtv.get(i).get("rj"), Integer.parseInt(rtv.get(i).get("count") == null ? "0" : ""+ rtv.get(i).get("count")));
                }
            }
            for(String key : rtvMap.keySet()) {
                recs.add(new DcRtTimeVehicleDTO("" + key, rtvMap.get(key)));
            }
        } else if("month".equals(tp)) { //一月
            String startTime = DateUtil.countDays(-29);
            rtv = dcRtMapper.getDcForDay(startTime, endTime);
            Map<String, Integer> rtvMap = DateUtil.dayFor30Map(29, DateUtil.DTF_NYR);
            if(rtv != null && rtv.size() > 0) {
                for(int i =0; i < rtv.size(); i++) {
                    rtvMap.put("" + rtv.get(i).get("rj"), Integer.parseInt(rtv.get(i).get("count") == null ? "0" : ""+ rtv.get(i).get("count")));
                }
            }
            Integer day =1;
            for(String key : rtvMap.keySet()) {
                recs.add(new DcRtTimeVehicleDTO("" + day, rtvMap.get(key)));
                day++;
            }
        } else if("quarter".equals(tp)) { //三月
            Map<String, Integer> rtvMap = fromQuarterToYear(89, endTime, 4,false);
            for(String key : rtvMap.keySet()) {
                recs.add(new DcRtTimeVehicleDTO(key, rtvMap.get(key)));
            }
        } else if("half".equals(tp)) { //半年
            Map<String, Integer> rtvMap = fromQuarterToYear(180, endTime, 7,false);
            for(String key : rtvMap.keySet()) {
                recs.add(new DcRtTimeVehicleDTO(key, rtvMap.get(key)));
            }
        } else if("year".equals(tp)) { //一年
            Map<String, Integer> rtvMap = fromQuarterToYear(365, endTime, 12,false);
            for(String key : rtvMap.keySet()) {
                recs.add(new DcRtTimeVehicleDTO(key, rtvMap.get(key)));
            }
        } else { //当天
            String startTime = DateUtil.countDays(0);
            rtv = dcRtMapper.getDcForDay(startTime, endTime);
            Map<String, Integer> rtvMap = DateUtil.timeFor24Map();
            if(rtv != null && rtv.size() > 0) {
                for(int i = 0; i < rtv.size(); i++) {
                    rtvMap.put("" + rtv.get(i).get("rj"), Integer.parseInt(rtv.get(i).get("count") == null ? "0" : ""+ rtv.get(i).get("count")));
                }
            }
            for(String key : rtvMap.keySet()) {
                recs.add(new DcRtTimeVehicleDTO(key,rtvMap.get(key)));
            }
        }
        return recs;
    }

    @Override
    public List<DcRtAreaVehicleDTO> getRav_exp(String tp) {
        List<DcRtAreaVehicleDTO> recs = new ArrayList<>();
        recs.add(new DcRtAreaVehicleDTO(101,"朝阳区",116.058931,40.060807,92346,0.1));
        recs.add(new DcRtAreaVehicleDTO(102,"海淀区",116.048931,40.060707,82346,0.2));
        recs.add(new DcRtAreaVehicleDTO(103,"东城区",116.038931,40.060607,72346,0.3));
        recs.add(new DcRtAreaVehicleDTO(104,"西城区",116.028931,40.060507,62346,0.4));
        recs.add(new DcRtAreaVehicleDTO(105,"昌平区",116.018931,40.060407,52346,0.5));
        recs.add(new DcRtAreaVehicleDTO(106,"丰台区",116.078931,40.060307,42346,0.6));
        recs.add(new DcRtAreaVehicleDTO(107,"密云区",116.080931,40.060207,12346,0.7));
        recs.add(new DcRtAreaVehicleDTO(108,"通州区",116.070931,40.060107,12346,0.8));
        recs.add(new DcRtAreaVehicleDTO(109,"石景山区",116.044931,40.062807,12346,0.3));
        recs.add(new DcRtAreaVehicleDTO(110,"门头沟区",116.033931,40.069807,12346,0.4));
        return recs;
    }


    private SortedMap<String, Integer> fromQuarterToYear(Integer num, String endTime, Integer length, boolean flag) {
        String startTime = DateUtil.countDays(-num);
        List<Map<String, Object>> rtv = dcRtMapper.getDcFor3m(startTime, endTime);
        SortedMap<String, Integer> rtvMap = DateUtil.monthFor3Map(length, flag);
        if(rtv != null && rtv.size() > 0) {
            for(int i =0; i < rtv.size(); i++) {
                rtvMap.put("" + rtv.get(i).get("rj"), Integer.parseInt(rtv.get(i).get("count") == null ? "0" : ""+ rtv.get(i).get("count")));
            }
        }
        return rtvMap;
    }

    private static Double str2Double(double num) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(num));
    }

}
