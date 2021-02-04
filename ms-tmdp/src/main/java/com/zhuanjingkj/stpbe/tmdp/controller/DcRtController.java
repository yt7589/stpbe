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
        List<DcRtRoadJamDTO> rrj = getRrj_exp(tp); //高峰时段拥堵路名排名
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

    private List<DcRtRoadJamDTO> getRrj_exp(String tp) {
        return dcRtService.getRrj_exp(tp);
    }
}
