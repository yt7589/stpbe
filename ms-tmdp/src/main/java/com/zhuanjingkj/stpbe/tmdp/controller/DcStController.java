package com.zhuanjingkj.stpbe.tmdp.controller;

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
        List<DcStVDTO> vst = getVst_exp(); // 过车车辆统计
        dcSt.setDcVSt(vst);
        List<DcStVAreaDTO> dcVArea = getVarea_exp(); //过车量地区前5
        dcSt.setDcVArea(dcVArea);
        List<DcStVSiteDTO> dcVSite = getVSite_exp(); //过车量点位前5
        dcSt.setDcVSite(dcVSite);
        List<DcStVTrendDTO> dcVTrend = getVTr_exp(); //过车量7日走势图
        dcSt.setDcVTrend(dcVTrend);
        List<DcStIlSiteDTO> dcIlSite = getIlSite_exp(); //违法点位前7
        dcSt.setDcIlSite(dcIlSite);
        List<DcStKvSiteDTO> dcKvSite = getDcKvs_exp(); //重点车辆违法点位前7
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
    private List<DcStVDTO> getVst_exp() {
        List<DcStVDTO> recs = new ArrayList<>();
        recs.add(new DcStVDTO("" + 1,120000,140000,110000));
        recs.add(new DcStVDTO("" + 2,140000,170000,130000));
        recs.add(new DcStVDTO("" + 3,150000,140000,140000));
        recs.add(new DcStVDTO("" + 4,160000,120000,150000));
        recs.add(new DcStVDTO("" + 5,170000,150000,160000));
        recs.add(new DcStVDTO("" + 6,180000,160000,170000));
        recs.add(new DcStVDTO("" + 7,190000,180000,150000));
        recs.add(new DcStVDTO("" + 8,100000,190000,180000));
        recs.add(new DcStVDTO("" + 9,110000,150000,140000));
        recs.add(new DcStVDTO("" + 10,120000,160000,130000));
        recs.add(new DcStVDTO("" + 11,140000,120000,120000));
        recs.add(new DcStVDTO("" + 12,150000,140000,150000));
        return recs;
    }

    private List<DcStVAreaDTO> getVarea_exp() {
        List<DcStVAreaDTO> recs = new ArrayList<>();
        recs.add(new DcStVAreaDTO(102,"西二旗",200000));
        recs.add(new DcStVAreaDTO(103,"上地",300000));
        recs.add(new DcStVAreaDTO(104,"西直门",400000));
        recs.add(new DcStVAreaDTO(105,"知春路",500000));
        recs.add(new DcStVAreaDTO(105,"北七家",600000));
        return recs;
    }

    private List<DcStVSiteDTO> getVSite_exp() {
        List<DcStVSiteDTO> recs = new ArrayList<>();
        recs.add(new DcStVSiteDTO(107,"上地四街",116.0854321,40.823654,100000));
        recs.add(new DcStVSiteDTO(107,"上地五街",116.0754321,40.723654,200000));
        recs.add(new DcStVSiteDTO(107,"上地六街",116.0654321,40.623654,300000));
        recs.add(new DcStVSiteDTO(107,"上地七街",116.0554321,40.523654,400000));
        recs.add(new DcStVSiteDTO(107,"上地八街",116.0454321,40.423654,500000));
        return recs;
    }

    private List<DcStVTrendDTO> getVTr_exp() {
        List<DcStVTrendDTO> recs = new ArrayList<>();
        recs.add(new DcStVTrendDTO("12-20", 100000));
        recs.add(new DcStVTrendDTO("12-21", 220000));
        recs.add(new DcStVTrendDTO("12-22", 240000));
        recs.add(new DcStVTrendDTO("12-23", 250000));
        recs.add(new DcStVTrendDTO("12-24", 240000));
        recs.add(new DcStVTrendDTO("12-25", 220000));
        recs.add(new DcStVTrendDTO("12-26", 210000));
        return recs;
    }

    private List<DcStIlSiteDTO> getIlSite_exp() {
        List<DcStIlSiteDTO> recs = new ArrayList<>();
        recs.add(new DcStIlSiteDTO(107,"西二旗",116.0454321,40.423654,111000));
        recs.add(new DcStIlSiteDTO(108,"上地",116.0554321,40.443654,100000));
        recs.add(new DcStIlSiteDTO(109,"西直门",116.0654321,40.463654,200000));
        recs.add(new DcStIlSiteDTO(101,"知春路",116.0754321,40.483654,300000));
        recs.add(new DcStIlSiteDTO(102,"东湖区",116.0854321,40.403654,400000));
        recs.add(new DcStIlSiteDTO(103,"北七家",116.0954321,40.413654,150000));
        recs.add(new DcStIlSiteDTO(104,"望京",116.1454321,40.553654,140000));
        return recs;
    }

    private List<DcStKvSiteDTO> getDcKvs_exp() {
        List<DcStKvSiteDTO> recs = new ArrayList<>();
        recs.add(new DcStKvSiteDTO(107,"海淀区上龙泽",116.1454321,40.553654,140000));
        recs.add(new DcStKvSiteDTO(108,"海淀区回龙观",116.2454321,40.653654,100000));
        recs.add(new DcStKvSiteDTO(109,"昌平区北七家",116.3454321,40.753654,110000));
        recs.add(new DcStKvSiteDTO(101,"海淀区西直门",116.4454321,40.853654,200000));
        recs.add(new DcStKvSiteDTO(102,"朝阳区东湖区",116.5454321,40.953654,300000));
        recs.add(new DcStKvSiteDTO(103,"海淀区上地",116.6454321,40.353654,200000));
        recs.add(new DcStKvSiteDTO(104,"海淀区西二旗",116.7454321,40.573654,100000));
        return recs;
    }

    private List<DcStTruckSiteDTO> getDcTruckSite_exp() {
        List<DcStTruckSiteDTO> recs = new ArrayList<>();
        recs.add(new DcStTruckSiteDTO(107,"海淀区西二旗",116.7454321,40.573654,50000));
        recs.add(new DcStTruckSiteDTO(108,"海淀区上龙泽",116.6454321,40.673654,100000));
        recs.add(new DcStTruckSiteDTO(109,"望京街道",116.5454321,40.773654,200000));
        recs.add(new DcStTruckSiteDTO(101,"朝阳区东湖区",116.4454321,40.873654,110000));
        recs.add(new DcStTruckSiteDTO(102,"海淀区知春路",116.3454321,40.5973654,70000));
        recs.add(new DcStTruckSiteDTO(103,"海淀区西直门",116.2454321,40.5573654,50000));
        recs.add(new DcStTruckSiteDTO(104,"海淀区上地",116.1454321,40.5273654,120000));
        return recs;
    }
}
