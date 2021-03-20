package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.mapper.DcRtMapper;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.*;
import com.zhuanjingkj.stpbe.tmdp.service.IDcRtService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private DcRtService dcRtService;

    @Override
    public List<DcRtTimeJamDTO> getRtj_exp(String tp) {
        List<DcRtTimeJamDTO> recs = new ArrayList<>();
        String endTime = DateUtil.countDays(0); //当前时间 2020-02-02
        String pEndTime = DateUtil.countDays(1); //结束时间
        if("week".equals(tp)) {  //近7天
            String startTime = DateUtil.plusDays(endTime, -6, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcForWeek(startTime, pEndTime);
            Integer total = dcRtMapper.getTivTotal(startTime, pEndTime); //7天总过车量
            Map<String, Integer> rtjForWeek = DateUtil.dayFor30Map(7, DateUtil.DTF_MD);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjForWeek.put("" + rtj.get(i).get("rj"), count);
                }
            }
            for(String key : rtjForWeek.keySet()) {
                recs.add(new DcRtTimeJamDTO(key, str2Double((double)rtjForWeek.get(key)/total)));
            }
        } else if("month".equals(tp)) { //近30天
            String startTime = DateUtil.plusDays(endTime, -29, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcForWeek(startTime, pEndTime);
            Integer total = dcRtMapper.getTivTotal(startTime, pEndTime); //30天总过车量
            Map<String, Integer> rtjFor4W = DateUtil.dayFor30Map(30, DateUtil.DTF_MD);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i <rtj.size(); i++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjFor4W.put("" + rtj.get(i).get("rj"), count);
                }
            }
            Integer day = 1;
            for(String key : rtjFor4W.keySet()) {
                recs.add(new DcRtTimeJamDTO("" + day, str2Double((double)rtjFor4W.get(key)/total)));
                day++;
            }
        } else if("quarter".equals(tp)) { //近90天
            String startTime = DateUtil.plusDays(endTime, -89, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcFor3m(startTime, pEndTime);
            Integer total = dcRtMapper.getTivTotal(startTime, pEndTime); //90天总过车量
            Map<String, Integer> rtjFor3m = DateUtil.monthFor3Map(4, false);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjFor3m.put("" + rtj.get(i).get("rj"), count);
                }
            }
            for(String key : rtjFor3m.keySet()) {
//                if(rtjFor3m.get(key) > 0) {
                    recs.add(new DcRtTimeJamDTO(key, str2Double((double)rtjFor3m.get(key)/total)));
//                }
            }
        } else if("half" .equals(tp)) { //近半年
            String startTime = DateUtil.plusDays(endTime, -180, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcFor3m(startTime, pEndTime);
            Integer total = dcRtMapper.getTivTotal(startTime, pEndTime); //180天总过车量
            Map<String, Integer> rtjFor3m = DateUtil.monthFor3Map(7, false);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjFor3m.put("" + rtj.get(i).get("rj"), count);
                }
            }
            for(String key : rtjFor3m.keySet()) {
//                if(rtjFor3m.get(key) > 0) {
                    recs.add(new DcRtTimeJamDTO(key, str2Double((double)rtjFor3m.get(key)/total)));
//                }
            }
        } else if("year".equals(tp)) { //近一年
            String startTime = DateUtil.plusDays(endTime, -365, DateUtil.DTF_NYR);
            List<Map<String, Object>> rtj = dcRtMapper.getDcFor3m(startTime, pEndTime);
            Integer total = dcRtMapper.getTivTotal(startTime, pEndTime); //365天总过车量
            Map<String, Integer> rtjFor3m = DateUtil.monthFor3Map(12, false);
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i++) {
                    Integer count =  Integer.parseInt(rtj.get(i).get("count") == null ? "0" : rtj.get(i).get("count") + "");
                    rtjFor3m.put("" + rtj.get(i).get("rj"), count);
                }
            }
            for(String key : rtjFor3m.keySet()) {
//                if(rtjFor3m.get(key) > 0) {
                    recs.add(new DcRtTimeJamDTO(key, str2Double((double)rtjFor3m.get(key)/total)));
//                }
            }
        } else {
            List<Map<String, Object>> rtj = dcRtMapper.getDcForDay(endTime, pEndTime);
            Integer total = dcRtMapper.getTivTotal(endTime, pEndTime); //当天过车量
            Map<String, Integer> rtjFor24Map = DateUtil.timeFor24Map();
            if(rtj != null && rtj.size() > 0) {
                for(int i = 0; i < rtj.size(); i ++) {
                    Integer count = Integer.parseInt(rtj.get(i).get("count") == null ? "0" : "" + rtj.get(i).get("count"));
                    String key = "" + rtj.get(i).get("rj");
                    if (StringUtils.isNotBlank(key)) {
                        key = (Integer.parseInt(key) + 1) < 10 ? "0" + (Integer.parseInt(key) + 1) : "" + (Integer.parseInt(key) + 1);
                    }
                    rtjFor24Map.put(key, count);
                }
            }
            for(String key : rtjFor24Map.keySet()) {

                if(total > 0) {
                    recs.add(new DcRtTimeJamDTO(key +":00", str2Double((double)rtjFor24Map.get(key)/total)));
                } else {
                    recs.add(new DcRtTimeJamDTO(key +":00", 0));
                }

            }
        }
        return recs;
    }

    @Override
    public List<DcRtAreaJamDTO> getRaj_exp(String tp) {
        List<DcRtAreaJamDTO> recs = new ArrayList<>();
        String endTime = DateUtil.countDays(1);
        List<Map<String, Object>> raj = new ArrayList<>();
        Integer total = 0;
        if("week".equals(tp)) { //一周
            String startTime = DateUtil.countDays(-6);
            String pStartTime = DateUtil.countDays(-13);
            raj = dcRtMapper.getRajForDay(startTime, endTime, pStartTime);
            total = dcRtMapper.getTivTotal(startTime, endTime); //一周总过车量
        } else if("month".equals(tp)) { //一月
            String startTime = DateUtil.countDays(-29);
            String pStartTime = DateUtil.countDays(-59);
            raj = dcRtMapper.getRajForDay(startTime, endTime, pStartTime);
            total = dcRtMapper.getTivTotal(startTime, endTime); //一月总过车量
        } else if("quarter".equals(tp)) { //三月
            String startTime = DateUtil.countDays(-89);
            String pStartTime = DateUtil.countDays(-179);
            raj = dcRtMapper.getRajForDay(startTime, endTime, pStartTime);
            total = dcRtMapper.getTivTotal(startTime, endTime); //三月总过车量
        } else if("half".equals(tp)) { //半年
            String startTime = DateUtil.countDays(-179);
            String pStartTime = DateUtil.countDays(-359);
            raj = dcRtMapper.getRajForDay(startTime, endTime, pStartTime);
            total = dcRtMapper.getTivTotal(startTime, endTime); //半年总过车量
        } else if("year".equals(tp)) { //一年
            String startTime = DateUtil.countDays(-364);
            String pStartTime = DateUtil.countDays(-729);
            raj = dcRtMapper.getRajForDay(startTime, endTime, pStartTime);
            total = dcRtMapper.getTivTotal(startTime, endTime); //一年总过车量
        } else { //当天
            String startTime = DateUtil.countDays(0);
            String pStartTime = DateUtil.countDays(-1);
            raj = dcRtMapper.getRajForDay(startTime, endTime, pStartTime);
            total = dcRtMapper.getTivTotal(startTime, endTime); //当天总过车量
        }
        if(raj != null && raj.size() > 0) {
            for(int i = 0; i < raj.size(); i++) {
                Integer count = Integer.parseInt(raj.get(i).get("mcount") == null ? "0" : "" + raj.get(i).get("mcount"));
                recs.add(new DcRtAreaJamDTO(0,"" + raj.get(i).get("area_name"),
                        str2Double(Double.parseDouble(raj.get(i).get("lng") == null ? "0" : "" + raj.get(i).get("lng"))),
                        str2Double(Double.parseDouble(raj.get(i).get("lat") == null ? "0" : "" + "" + raj.get(i).get("lat"))),
                        str2Double((double) count/total),
                        str2Double(Double.parseDouble(raj.get(i).get("rg") == null ? "0" : "" + raj.get(i).get("rg")))));
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
            rtv = dcRtMapper.getRtvForDay(startTime, endTime);
            Map<String, Integer> rtvMap = DateUtil.dayFor30Map(7, DateUtil.DTF_MD);
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
            rtv = dcRtMapper.getRtvForDay(startTime, endTime);
            Map<String, Integer> rtvMap = DateUtil.dayFor30Map(30, DateUtil.DTF_MD);
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
                    String key = "" + rtv.get(i).get("rj");
                    if (StringUtils.isNotBlank(key)) {
                        key = (Integer.parseInt(key) + 1) < 10 ? "0" + (Integer.parseInt(key) + 1) : "" + (Integer.parseInt(key) + 1);
                    }
                    rtvMap.put(key +":00", Integer.parseInt(rtv.get(i).get("count") == null ? "0" : ""+ rtv.get(i).get("count")));
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
        String endTime = DateUtil.countDays(1); //结束时间
        List<Map<String, Object>> rav = new ArrayList<>();
        if("week".equals(tp)) { //一周
            String startTime = DateUtil.countDays(-6);
            String pStartTime = DateUtil.countDays(-13);
            rav = dcRtMapper.getRavForDay(startTime, endTime, pStartTime);
        } else if("month".equals(tp)) { //一月
            String startTime = DateUtil.countDays(-29);
            String pStartTime = DateUtil.countDays(-59);
            rav = dcRtMapper.getRavForDay(startTime, endTime, pStartTime);
        } else if("quarter".equals(tp)) { //三月
            String startTime = DateUtil.countDays(-89);
            String pStartTime = DateUtil.countDays(-179);
            rav = dcRtMapper.getRavForDay(startTime, endTime, pStartTime);
        } else if("half".equals(tp)) { //半年
            String startTime = DateUtil.countDays(-179);
            String pStartTime = DateUtil.countDays(-359);
            rav = dcRtMapper.getRavForDay(startTime, endTime, pStartTime);
        } else if("year".equals(tp)) { //一年
            String startTime = DateUtil.countDays(-364);
            String pStartTime = DateUtil.countDays(-729);
            rav = dcRtMapper.getRavForDay(startTime, endTime, pStartTime);
        } else { //当天
            String startTime = DateUtil.countDays(0);
            String pStartTime = DateUtil.countDays(-1);
            rav = dcRtMapper.getRavForDay(startTime, endTime, pStartTime);
        }
        if(rav != null && rav.size() > 0) {
            for(int i = 0; i < rav.size(); i++) {
                recs.add(new DcRtAreaVehicleDTO(0,"" + rav.get(i).get("mAreaName"),
                        str2Double(Double.parseDouble(rav.get(i).get("lng") == null ? "0" : "" + rav.get(i).get("lng"))),
                        str2Double(Double.parseDouble(rav.get(i).get("lat") == null ? "0" : "" + rav.get(i).get("lat"))),
                        Integer.parseInt(rav.get(i).get("mcount") == null ? "0" : "" + rav.get(i).get("mcount")),
                        str2Double(Double.parseDouble(rav.get(i).get("rg") == null ? "0" :"" + rav.get(i).get("rg")))));
            }
        }
        return recs;
    }

    @Override
    public List<DcRtRoadJamDTO> getRrj_exp(String tp) {
        List<DcRtRoadJamDTO> recs = new ArrayList<>();
        String endTime = DateUtil.countDays(1); //结束时间
        List<Map<String, Object>> rrj = new ArrayList<>();
        Integer count = 0;
        if("week".equals(tp)) { //一周
            String startTime = DateUtil.countDays(-6);
            String pStartTime = DateUtil.countDays(-13);
            rrj = dcRtMapper.getRrjForDay(startTime, endTime, pStartTime);
            count = dcRtMapper.getTivTotal(startTime, endTime);
        } else if("month".equals(tp)) { //一月
            String startTime = DateUtil.countDays(-29);
            String pStartTime = DateUtil.countDays(-59);
            rrj = dcRtMapper.getRrjForDay(startTime, endTime, pStartTime);
            count = dcRtMapper.getTivTotal(startTime, endTime);
        } else if("quarter".equals(tp)) { //三月
            String startTime = DateUtil.countDays(-89);
            String pStartTime = DateUtil.countDays(-179);
            rrj = dcRtMapper.getRrjForDay(startTime, endTime, pStartTime);
            count = dcRtMapper.getTivTotal(startTime, endTime);
        } else if("half".equals(tp)) { //半年
            String startTime = DateUtil.countDays(-179);
            String pStartTime = DateUtil.countDays(-359);
            rrj = dcRtMapper.getRrjForDay(startTime, endTime, pStartTime);
            count = dcRtMapper.getTivTotal(startTime, endTime);
        } else if("year".equals(tp)) { //一年
            String startTime = DateUtil.countDays(-364);
            String pStartTime = DateUtil.countDays(-729);
            rrj = dcRtMapper.getRrjForDay(startTime, endTime, pStartTime);
            count = dcRtMapper.getTivTotal(startTime, endTime);
        } else { //当天
            String startTime = DateUtil.countDays(0);
            String pStartTime = DateUtil.countDays(-1);
            rrj = dcRtMapper.getRrjForDay(startTime, endTime, pStartTime);
            count = dcRtMapper.getTivTotal(startTime, endTime);
        }
        if(rrj != null && rrj.size() > 0) {
            for(int i = 0; i < rrj.size(); i++) {
                recs.add(new DcRtRoadJamDTO(0,""+rrj.get(i).get("road_name"),
                        str2Double(Double.parseDouble(rrj.get(i).get("srcLng") == null ? "0" : "" + rrj.get(i).get("srcLng"))),
                        str2Double(Double.parseDouble(rrj.get(i).get("srcLat") == null ? "0" : "" + rrj.get(i).get("srcLat"))),
                        str2Double(Double.parseDouble(rrj.get(i).get("dstLng") == null ? "0" : "" + rrj.get(i).get("dstLng"))),
                        str2Double(Double.parseDouble(rrj.get(i).get("dstLat") == null ? "0" : "" + rrj.get(i).get("dstLat"))),
                        str2Double(Double.parseDouble(rrj.get(i).get("mcount") == null ? "0" : "" + rrj.get(i).get("mcount"))/count),
                        str2Double(Double.parseDouble(rrj.get(i).get("rg") == null ? "0" : "" + rrj.get(i).get("rg")))));
            }
        }
        return recs;
    }

    @Override
    public ResultDTO<DcRtDTO> queryDataReport_exp(String tp) {
        ResultDTO<DcRtDTO> dto = new ResultDTO<>();
        DcRtDTO data = new DcRtDTO();
        if ("today".equals(tp)) {
            data.setRaj(dcRtService.getRaj_exp(tp));
            data.setRav(dcRtService.getRav_exp(tp));
            data.setRrj(dcRtService.getRrj_exp(tp));
            data.setRtj(dcRtService.getRtj_exp(tp));
            data.setRtv(dcRtService.getRtv_exp(tp));
        } else {
            Map<String, Object> resMap = dcRtMapper.getDcRt(tp, LocalDate.now().toString());
            if (resMap !=null && resMap.size() > 0) {
                List<DcRtTimeJamDTO> rtj = JSON.parseArray(resMap.get("rt_rtj").toString(), DcRtTimeJamDTO.class); //分时段拥堵趋势
                List<DcRtAreaJamDTO> raj = JSON.parseArray(resMap.get("rt_raj").toString(), DcRtAreaJamDTO.class) ; //分区高峰时段拥堵排名
                List<DcRtTimeVehicleDTO> rtv = JSON.parseArray(resMap.get("rt_rtv").toString(), DcRtTimeVehicleDTO.class) ; //分时段过车量
                List<DcRtAreaVehicleDTO> rav = JSON.parseArray(resMap.get("rt_rav").toString(), DcRtAreaVehicleDTO.class) ; //分区过车量排名
                List<DcRtRoadJamDTO> rrj = JSON.parseArray(resMap.get("rt_rrj").toString(), DcRtRoadJamDTO.class) ; //高峰时段拥堵路名排名
                data.setRaj(raj);
                data.setRav(rav);
                data.setRrj(rrj);
                data.setRtj(rtj);
                data.setRtv(rtv);
            }
        }
        dto.setData(data);
        return dto;
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
