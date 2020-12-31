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
        recs.add(new DcStVSiteDTO(107,"上地四街",88,99,100000));
        recs.add(new DcStVSiteDTO(107,"上地五街",77,88,200000));
        recs.add(new DcStVSiteDTO(107,"上地六街",66,77,300000));
        recs.add(new DcStVSiteDTO(107,"上地七街",55,66,400000));
        recs.add(new DcStVSiteDTO(107,"上地八街",44,55,500000));
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
        recs.add(new DcStIlSiteDTO(107,"上地八街",44,55,500000));
        recs.add(new DcStIlSiteDTO(108,"上地四街",88,99,100000));
        recs.add(new DcStIlSiteDTO(109,"上地五街",77,88,200000));
        recs.add(new DcStIlSiteDTO(101,"上地六街",66,77,300000));
        recs.add(new DcStIlSiteDTO(102,"上地七街",55,66,400000));
        recs.add(new DcStIlSiteDTO(103,"上地八街",44,55,500000));
        recs.add(new DcStIlSiteDTO(104,"上地八街",44,55,500000));
        return recs;
    }

    private List<DcStKvSiteDTO> getDcKvs_exp() {
        List<DcStKvSiteDTO> recs = new ArrayList<>();
        recs.add(new DcStKvSiteDTO(107,"上地八街",44,55,500000));
        recs.add(new DcStKvSiteDTO(108,"上地四街",88,99,100000));
        recs.add(new DcStKvSiteDTO(109,"上地五街",77,88,200000));
        recs.add(new DcStKvSiteDTO(101,"上地六街",66,77,300000));
        recs.add(new DcStKvSiteDTO(102,"上地七街",55,66,400000));
        recs.add(new DcStKvSiteDTO(103,"上地八街",44,55,500000));
        recs.add(new DcStKvSiteDTO(104,"上地九街",44,55,500000));
        return recs;
    }

    private List<DcStTruckSiteDTO> getDcTruckSite_exp() {
        List<DcStTruckSiteDTO> recs = new ArrayList<>();
        recs.add(new DcStTruckSiteDTO(107,"上地八街",44,55,500000));
        recs.add(new DcStTruckSiteDTO(108,"上地四街",88,99,100000));
        recs.add(new DcStTruckSiteDTO(109,"上地五街",77,88,200000));
        recs.add(new DcStTruckSiteDTO(101,"上地六街",66,77,300000));
        recs.add(new DcStTruckSiteDTO(102,"上地七街",55,66,400000));
        recs.add(new DcStTruckSiteDTO(103,"上地八街",44,55,500000));
        recs.add(new DcStTruckSiteDTO(104,"上地九街",44,55,500000));
        return recs;
    }
}
