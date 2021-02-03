package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DcRtService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DcRtService dcRtService;

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
        return queryDataReport_exp(tp);
    }

    private ResultDTO<DcRtDTO> queryDataReport_exp(String tp) {
        ResultDTO<DcRtDTO> dto = new ResultDTO<>();
        List<DcRtTimeJamDTO> rtj = getRtj_exp(tp); //分时段拥堵趋势
        List<DcRtAreaJamDTO> raj = getRaj_exp(tp); //分区高峰时段拥堵排名
        List<DcRtTimeVehicleDTO> rtv = getRtv_exp(tp); //分时段过车量
        List<DcRtAreaVehicleDTO> rav = getRav_exp(tp); //分区过车量排名
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

    private List<DcRtTimeJamDTO> getRtj_exp(String tp) {
        return dcRtService.getRtj_exp(tp);
    }

    private List<DcRtAreaJamDTO> getRaj_exp(String tp) {
        return dcRtService.getRaj_exp(tp);
    }

    private List<DcRtTimeVehicleDTO> getRtv_exp(String tp) {
        return dcRtService.getRtv_exp(tp);
    }

    private List<DcRtAreaVehicleDTO> getRav_exp(String tp) {
        return dcRtService.getRav_exp(tp);
    }

    private List<DcRtRoadJamDTO> getRrj_exp() {
        List<DcRtRoadJamDTO> recs = new ArrayList<>();
        recs.add(new DcRtRoadJamDTO(101,"朝阳区三里屯街道",116.033931,40.081807,0.3,0.1));
        recs.add(new DcRtRoadJamDTO(102,"海淀区海淀街道",116.032931,40.082807,0.4,0.2));
        recs.add(new DcRtRoadJamDTO(103,"东城区东四街道",116.031931,40.063807,0.5,0.3));
        recs.add(new DcRtRoadJamDTO(104,"西城区德胜街道",116.034931,40.064807,0.6,0.4));
        recs.add(new DcRtRoadJamDTO(105,"昌平区城北街道",116.035931,40.065807,0.7,0.5));
        recs.add(new DcRtRoadJamDTO(106,"丰台区丰台街道",116.036931,40.066807,0.2,0.6));
        recs.add(new DcRtRoadJamDTO(107,"密云区鼓楼街道",116.037931,40.067807,0.5,0.7));
        recs.add(new DcRtRoadJamDTO(108,"通州区玉桥街道",116.038931,40.0618907,0.4,0.8));
        recs.add(new DcRtRoadJamDTO(109,"石景山区八宝山街道",116.039931,40.0169807,0.7,0.3));
        recs.add(new DcRtRoadJamDTO(110,"门头沟区大峪街道",116.036031,40.0269807,0.8,0.4));
        return recs;
    }
}
