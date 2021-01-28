package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DcStIlSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.DcStVAreaDTO;
import com.zhuanjingkj.stpbe.data.dto.DcStVSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DcStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Data center =》 Data Statistics
 * 数据中心 =》 数据统计
 */
@RestController
@RequestMapping(value = "/dc")
@CrossOrigin(origins = "*")
public class DcStController {

    @Autowired
    private DcStService dcStService;

    /**
     * 数据统计
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/st/queryDataStatistics")
    public ResultDTO<DcStDTO> queryDataStatistics(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        ResultDTO<DcStDTO> dto = new ResultDTO<DcStDTO>();
        DcStDTO dcSt = new DcStDTO();
        DcStSysDTO dcSys = getSys_exp();  //系统概况
        DcStTodayDTO dcToday = getStToday_exp(); //今日概况
        dcSt.setDcToday(dcToday);
        DcStVDTO vst = getVst_exp(); // 过车车辆统计
        dcSt.setDcVSt(vst);
        List<DcStVAreaDTO> dcVArea = getVarea_exp(); //过车量地区前5
        dcSt.setDcVArea(dcVArea);
        List<DcStVSiteDTO> dcVSite = getVSite_exp(); //过车量点位前5
        dcSt.setDcVSite(dcVSite);
        List<DcStVTrendDTO> dcVTrend = getVTr_exp(); //过车量7日走势图
        dcSt.setDcVTrend(dcVTrend);
        List<DcStIlSiteDTO> dcIlSite = getIlSite_exp(); //违法点位前7
        dcSt.setDcIlSite(dcIlSite);
        List<DcStKvSiteDTO> dcKvSite = getDcKvs_exp(); //重点车辆点位前7
        dcSt.setDcKvSite(dcKvSite);
        List<DcStTruckSiteDTO> dcTsSite = getDcTruckSite_exp(); //大货车点位前7
        dcSt.setDcTsSite(dcTsSite);
        dcSt.setDcSys(dcSys);
        dto.setData(dcSt);
        return dto;
    }

    private DcStSysDTO getSys_exp() {
       return dcStService.getSys_exp();
    }

    private DcStTodayDTO getStToday_exp() {
       return dcStService.getStToday_exp();
    }

    private DcStVDTO getVst_exp() {
        return dcStService.getVst_exp();
    }

    private List<DcStVAreaDTO> getVarea_exp() {
        return dcStService.getVarea_exp();
    }

    private List<DcStVSiteDTO> getVSite_exp() {
        return dcStService.getVSite_exp();
    }

    private List<DcStVTrendDTO> getVTr_exp() {
        return dcStService.getVTr_exp();
    }

    private List<DcStIlSiteDTO> getIlSite_exp() {
        return dcStService.getIlSite_exp();
    }

    private List<DcStKvSiteDTO> getDcKvs_exp() {
        return dcStService.getDcKvs_exp();
    }

    private List<DcStTruckSiteDTO> getDcTruckSite_exp() {
        return dcStService.getDcTruckSite_exp();
    }
}
