package com.zhuanjingkj.stpbe.tmdp.controller;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
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
        DcSysDTO dcSys = new DcSysDTO(200,200,1,200,200,200); //系统概况
        DcTodayDTO dcToday = new DcTodayDTO(123456,12346,500,70,65); //今日概况
        dcSt.setDcToday(dcToday);
        List<DcVstDTO> vst = getVst_exp(); // 过车车辆统计
        dcSt.setDcVSt(vst);
        List<DcVAreaDTO> dcVArea = getVarea_exp(); //过车量地区前5
        dcSt.setDcVArea(dcVArea);
        List<DcVSiteDTO> dcVSite = getVSite_exp(); //过车量点位前5
        dcSt.setDcVSite(dcVSite);
        List<DcVTrendDTO> dcVTrend = getVTr_exp(); //过车量7日走势图
        dcSt.setDcVTrend(dcVTrend);
        List<DcIlSiteDTO> dcIlSite = getIlSite_exp(); //违法点位前7
        dcSt.setDcIlSite(dcIlSite);
        List<DcKvSiteDTO> dcKvSite = getDcKvs_exp(); //重点车辆违法点位前7
        dcSt.setDcKvSite(dcKvSite);
        List<DcTruckSiteDTO> dcTsSite = getDcTruckSite_exp(); //大货车点位前7
        dcSt.setDcTsSite(dcTsSite);
        dcSt.setDcSys(dcSys);
        dto.setData(dcSt);
        return dto;
    }

    private List<DcVstDTO> getVst_exp() {
        List<DcVstDTO> recs = new ArrayList<>();
        recs.add(new DcVstDTO("" + 1,120000,140000,150000));
        recs.add(new DcVstDTO("" + 2,120000,140000,150000));
        recs.add(new DcVstDTO("" + 3,120000,140000,150000));
        recs.add(new DcVstDTO("" + 4,120000,140000,150000));
        recs.add(new DcVstDTO("" + 5,120000,140000,150000));
        recs.add(new DcVstDTO("" + 6,120000,140000,150000));
        recs.add(new DcVstDTO("" + 7,120000,140000,150000));
        recs.add(new DcVstDTO("" + 8,120000,140000,150000));
        recs.add(new DcVstDTO("" + 9,120000,140000,150000));
        recs.add(new DcVstDTO("" + 10,120000,140000,150000));
        recs.add(new DcVstDTO("" + 11,120000,140000,150000));
        recs.add(new DcVstDTO("" + 12,120000,140000,150000));
        return recs;
    }

    private List<DcVAreaDTO> getVarea_exp() {
        List<DcVAreaDTO> recs = new ArrayList<>();
        recs.add(new DcVAreaDTO(102,"上地四街",200000));
        recs.add(new DcVAreaDTO(103,"上地五街",300000));
        recs.add(new DcVAreaDTO(104,"上地六街",400000));
        recs.add(new DcVAreaDTO(105,"上地七街",500000));
        recs.add(new DcVAreaDTO(105,"上地八街",600000));
        return recs;
    }

    private List<DcVSiteDTO> getVSite_exp() {
        List<DcVSiteDTO> recs = new ArrayList<>();
        recs.add(new DcVSiteDTO(107,"上地四街",88,99,100000));
        recs.add(new DcVSiteDTO(107,"上地五街",77,88,200000));
        recs.add(new DcVSiteDTO(107,"上地六街",66,77,300000));
        recs.add(new DcVSiteDTO(107,"上地七街",55,66,400000));
        recs.add(new DcVSiteDTO(107,"上地八街",44,55,500000));
        return recs;
    }

    private List<DcVTrendDTO> getVTr_exp() {
        List<DcVTrendDTO> recs = new ArrayList<>();
        recs.add(new DcVTrendDTO("12-20", 200000));
        recs.add(new DcVTrendDTO("12-21", 200000));
        recs.add(new DcVTrendDTO("12-22", 200000));
        recs.add(new DcVTrendDTO("12-23", 200000));
        recs.add(new DcVTrendDTO("12-24", 200000));
        recs.add(new DcVTrendDTO("12-25", 200000));
        recs.add(new DcVTrendDTO("12-26", 200000));
        return recs;
    }

    private List<DcIlSiteDTO> getIlSite_exp() {
        List<DcIlSiteDTO> recs = new ArrayList<>();
        recs.add(new DcIlSiteDTO(107,"上地八街",44,55,500000));
        recs.add(new DcIlSiteDTO(108,"上地四街",88,99,100000));
        recs.add(new DcIlSiteDTO(109,"上地五街",77,88,200000));
        recs.add(new DcIlSiteDTO(101,"上地六街",66,77,300000));
        recs.add(new DcIlSiteDTO(102,"上地七街",55,66,400000));
        recs.add(new DcIlSiteDTO(103,"上地八街",44,55,500000));
        recs.add(new DcIlSiteDTO(104,"上地八街",44,55,500000));
        return recs;
    }

    private List<DcKvSiteDTO> getDcKvs_exp() {
        List<DcKvSiteDTO> recs = new ArrayList<>();
        recs.add(new DcKvSiteDTO(107,"上地八街",44,55,500000));
        recs.add(new DcKvSiteDTO(108,"上地四街",88,99,100000));
        recs.add(new DcKvSiteDTO(109,"上地五街",77,88,200000));
        recs.add(new DcKvSiteDTO(101,"上地六街",66,77,300000));
        recs.add(new DcKvSiteDTO(102,"上地七街",55,66,400000));
        recs.add(new DcKvSiteDTO(103,"上地八街",44,55,500000));
        recs.add(new DcKvSiteDTO(104,"上地九街",44,55,500000));
        return recs;
    }

    private List<DcTruckSiteDTO> getDcTruckSite_exp() {
        List<DcTruckSiteDTO> recs = new ArrayList<>();
        recs.add(new DcTruckSiteDTO(107,"上地八街",44,55,500000));
        recs.add(new DcTruckSiteDTO(108,"上地四街",88,99,100000));
        recs.add(new DcTruckSiteDTO(109,"上地五街",77,88,200000));
        recs.add(new DcTruckSiteDTO(101,"上地六街",66,77,300000));
        recs.add(new DcTruckSiteDTO(102,"上地七街",55,66,400000));
        recs.add(new DcTruckSiteDTO(103,"上地八街",44,55,500000));
        recs.add(new DcTruckSiteDTO(104,"上地九街",44,55,500000));
        return recs;
    }
}
