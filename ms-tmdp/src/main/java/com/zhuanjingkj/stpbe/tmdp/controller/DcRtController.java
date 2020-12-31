package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.*;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data center =》 Data Report
 * 数据中心 =》 数据报告
 */
@RestController
@RequestMapping(value = "/dc")
@CrossOrigin(origins = "*")
public class DcRtController {

    /**
     * 数据报告
     * @param platform
     * @param version
     * @param tp
     * @return
     */
    @GetMapping(value = "/rt/queryDataReport")
    public ResultDTO<DcRtDTO> queryDataReport(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "tp", required = false) String tp
    ) {
        Integer day = 1;
        if("week".equals(tp)) {  //近7天
            day = 7;
        } else if("month".equals(tp)) { //近30天
            day = 30;
        } else if("quarter".equals(tp)) { //近90天
            day = 90;
        } else if("half" .equals(tp)) { //近半年
            day = 180;
        } else if("year".equals(tp)) { //近一年
            day = 365;
        }
        return queryDataReport_exp(day);
    }

    private ResultDTO<DcRtDTO> queryDataReport_exp(Integer day) {
        ResultDTO<DcRtDTO> dto = new ResultDTO<>();
        List<DcRtTimeJamDTO> rtj = getRtj_exp(); //分时段拥堵趋势
        List<DcRtAreaJamDTO> raj = getRaj_exp(); //分区高峰时段拥堵排名
        List<DcRtTimeVehicleDTO> rtv = getRtv_exp(); //分时段过车量
        List<DcRtAreaVehicleDTO> rav = getRav_exp(); //分区过车量排名
        List<DcRtRoadJamDTO> rrj = getRrj_exp(); //高峰时段拥堵路名排名
        DcRtDTO data = new DcRtDTO();
        data.setRaj(raj);
        data.setRav(rav);
        data.setRrj(rrj);
        data.setRtj(rtj);
        data.setRtv(rtv);
        dto.setData(data);
        return dto;
    }

    private List<DcRtTimeJamDTO> getRtj_exp() {
        List<DcRtTimeJamDTO> recs = new ArrayList<>();
        for(int i = 0; i < 24; i ++) {
            recs.add(new DcRtTimeJamDTO("" + (i < 10 ? "0" + i : i) + ":00", (i + 1) * 0.1));
        }
        return recs;
    }

    private List<DcRtAreaJamDTO> getRaj_exp() {
        List<DcRtAreaJamDTO> recs = new ArrayList<>();
        recs.add(new DcRtAreaJamDTO(101,"朝阳区",50,140,0.3,0.1));
        recs.add(new DcRtAreaJamDTO(102,"海淀区",60,130,0.4,0.2));
        recs.add(new DcRtAreaJamDTO(103,"东城区",70,120,0.5,0.3));
        recs.add(new DcRtAreaJamDTO(104,"西城区",80,110,0.6,0.4));
        recs.add(new DcRtAreaJamDTO(105,"昌平区",90,100,0.7,0.5));
        recs.add(new DcRtAreaJamDTO(106,"丰台区",100,90,0.2,0.6));
        recs.add(new DcRtAreaJamDTO(107,"密云区",110,80,0.5,0.7));
        recs.add(new DcRtAreaJamDTO(108,"通州区",120,70,0.4,0.8));
        recs.add(new DcRtAreaJamDTO(109,"石景山区",130,60,0.7,0.3));
        recs.add(new DcRtAreaJamDTO(110,"门头沟区",140,50,0.8,0.4));
        return recs;
    }

    private List<DcRtTimeVehicleDTO> getRtv_exp() {
        List<DcRtTimeVehicleDTO> recs = new ArrayList<>();
        for(int i = 0; i < 24; i ++) {
            recs.add(new DcRtTimeVehicleDTO("" + (i < 10 ? "0" + i : i) + ":00", (i + 1) * 1000000));
        }
        return recs;
    }

    private List<DcRtAreaVehicleDTO> getRav_exp() {
        List<DcRtAreaVehicleDTO> recs = new ArrayList<>();
        recs.add(new DcRtAreaVehicleDTO(101,"朝阳区",50,140,92346,0.1));
        recs.add(new DcRtAreaVehicleDTO(102,"海淀区",60,130,82346,0.2));
        recs.add(new DcRtAreaVehicleDTO(103,"东城区",70,120,72346,0.3));
        recs.add(new DcRtAreaVehicleDTO(104,"西城区",80,110,62346,0.4));
        recs.add(new DcRtAreaVehicleDTO(105,"昌平区",90,100,52346,0.5));
        recs.add(new DcRtAreaVehicleDTO(106,"丰台区",100,90,42346,0.6));
        recs.add(new DcRtAreaVehicleDTO(107,"密云区",110,80,12346,0.7));
        recs.add(new DcRtAreaVehicleDTO(108,"通州区",120,70,12346,0.8));
        recs.add(new DcRtAreaVehicleDTO(109,"石景山区",130,60,12346,0.3));
        recs.add(new DcRtAreaVehicleDTO(110,"门头沟区",140,50,12346,0.4));
        return recs;
    }

    private List<DcRtRoadJamDTO> getRrj_exp() {
        List<DcRtRoadJamDTO> recs = new ArrayList<>();
        recs.add(new DcRtRoadJamDTO(101,"朝阳区三里屯街道",50,140,0.3,0.1));
        recs.add(new DcRtRoadJamDTO(102,"海淀区海淀街道",60,130,0.4,0.2));
        recs.add(new DcRtRoadJamDTO(103,"东城区东四街道",70,120,0.5,0.3));
        recs.add(new DcRtRoadJamDTO(104,"西城区德胜街道",80,110,0.6,0.4));
        recs.add(new DcRtRoadJamDTO(105,"昌平区城北街道",90,100,0.7,0.5));
        recs.add(new DcRtRoadJamDTO(106,"丰台区丰台街道",100,90,0.2,0.6));
        recs.add(new DcRtRoadJamDTO(107,"密云区鼓楼街道",110,80,0.5,0.7));
        recs.add(new DcRtRoadJamDTO(108,"通州区玉桥街道",120,70,0.4,0.8));
        recs.add(new DcRtRoadJamDTO(109,"石景山区八宝山街道",130,60,0.7,0.3));
        recs.add(new DcRtRoadJamDTO(110,"门头沟区大峪街道",140,50,0.8,0.4));
        return recs;
    }
}
