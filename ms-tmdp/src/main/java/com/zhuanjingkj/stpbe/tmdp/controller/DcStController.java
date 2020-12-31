package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.*;
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

    /**
     * 数据统计
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/st/queryDataStatistics")
    public ResultDTO<DcStDTO> queryDataStatistics(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version
    ) {
        ResultDTO<DcStDTO> dto = new ResultDTO<DcStDTO>();
        DcStDTO dcSt = new DcStDTO();
        DcStSysDTO dcSys = new DcStSysDTO(200,200,1,200,200,200); //系统概况
        DcStTodayDTO dcToday = new DcStTodayDTO(123456,12346,500,70,65); //今日概况
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

    private List<DcStVDTO> getVst_exp() {
        List<DcStVDTO> recs = new ArrayList<>();
        recs.add(new DcStVDTO("" + 1,120000,140000,150000));
        recs.add(new DcStVDTO("" + 2,120000,140000,150000));
        recs.add(new DcStVDTO("" + 3,120000,140000,150000));
        recs.add(new DcStVDTO("" + 4,120000,140000,150000));
        recs.add(new DcStVDTO("" + 5,120000,140000,150000));
        recs.add(new DcStVDTO("" + 6,120000,140000,150000));
        recs.add(new DcStVDTO("" + 7,120000,140000,150000));
        recs.add(new DcStVDTO("" + 8,120000,140000,150000));
        recs.add(new DcStVDTO("" + 9,120000,140000,150000));
        recs.add(new DcStVDTO("" + 10,120000,140000,150000));
        recs.add(new DcStVDTO("" + 11,120000,140000,150000));
        recs.add(new DcStVDTO("" + 12,120000,140000,150000));
        return recs;
    }

    private List<DcStVAreaDTO> getVarea_exp() {
        List<DcStVAreaDTO> recs = new ArrayList<>();
        recs.add(new DcStVAreaDTO(102,"上地四街",200000));
        recs.add(new DcStVAreaDTO(103,"上地五街",300000));
        recs.add(new DcStVAreaDTO(104,"上地六街",400000));
        recs.add(new DcStVAreaDTO(105,"上地七街",500000));
        recs.add(new DcStVAreaDTO(105,"上地八街",600000));
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
        recs.add(new DcStVTrendDTO("12-20", 200000));
        recs.add(new DcStVTrendDTO("12-21", 200000));
        recs.add(new DcStVTrendDTO("12-22", 200000));
        recs.add(new DcStVTrendDTO("12-23", 200000));
        recs.add(new DcStVTrendDTO("12-24", 200000));
        recs.add(new DcStVTrendDTO("12-25", 200000));
        recs.add(new DcStVTrendDTO("12-26", 200000));
        return recs;
    }

    private List<DcStIlSiteDTO> getIlSite_exp() {
        List<DcStIlSiteDTO> recs = new ArrayList<>();
        recs.add(new DcStIlSiteDTO(107,"上地八街",116.0454321,40.423654,500000));
        recs.add(new DcStIlSiteDTO(108,"上地四街",116.0554321,40.443654,100000));
        recs.add(new DcStIlSiteDTO(109,"上地五街",116.0654321,40.463654,200000));
        recs.add(new DcStIlSiteDTO(101,"上地六街",116.0754321,40.483654,300000));
        recs.add(new DcStIlSiteDTO(102,"上地七街",116.0854321,40.403654,400000));
        recs.add(new DcStIlSiteDTO(103,"上地八街",116.0954321,40.413654,500000));
        recs.add(new DcStIlSiteDTO(104,"上地八街",116.1454321,40.553654,500000));
        return recs;
    }

    private List<DcStKvSiteDTO> getDcKvs_exp() {
        List<DcStKvSiteDTO> recs = new ArrayList<>();
        recs.add(new DcStKvSiteDTO(107,"上地八街",116.1454321,40.553654,500000));
        recs.add(new DcStKvSiteDTO(108,"上地四街",116.2454321,40.653654,100000));
        recs.add(new DcStKvSiteDTO(109,"上地五街",116.3454321,40.753654,200000));
        recs.add(new DcStKvSiteDTO(101,"上地六街",116.4454321,40.853654,300000));
        recs.add(new DcStKvSiteDTO(102,"上地七街",116.5454321,40.953654,400000));
        recs.add(new DcStKvSiteDTO(103,"上地八街",116.6454321,40.353654,500000));
        recs.add(new DcStKvSiteDTO(104,"上地九街",116.7454321,40.573654,500000));
        return recs;
    }

    private List<DcStTruckSiteDTO> getDcTruckSite_exp() {
        List<DcStTruckSiteDTO> recs = new ArrayList<>();
        recs.add(new DcStTruckSiteDTO(107,"上地八街",116.7454321,40.573654,500000));
        recs.add(new DcStTruckSiteDTO(108,"上地四街",116.6454321,40.673654,100000));
        recs.add(new DcStTruckSiteDTO(109,"上地五街",116.5454321,40.773654,200000));
        recs.add(new DcStTruckSiteDTO(101,"上地六街",116.4454321,40.873654,300000));
        recs.add(new DcStTruckSiteDTO(102,"上地七街",116.3454321,40.5973654,400000));
        recs.add(new DcStTruckSiteDTO(103,"上地八街",116.2454321,40.5573654,500000));
        recs.add(new DcStTruckSiteDTO(104,"上地九街",116.1454321,40.5273654,500000));
        return recs;
    }
}
