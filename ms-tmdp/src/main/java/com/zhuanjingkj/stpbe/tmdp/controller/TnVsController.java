package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsSiteDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsTopSiteDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsTopVehicleDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Traffic Network => Vehicle statistics
 * 路网实况 =》 路网车辆统计
 */
@RestController
@RequestMapping("/tn")
@CrossOrigin(origins = "*")
public class TnVsController {

    /**
     * 路网过车统计
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/vs/queryVehicleStatistics")
    public ResultDTO<TnVsDTO> queryEquipment(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        ResultDTO<TnVsDTO> dto = new ResultDTO<TnVsDTO>();
        TnVsDTO tnVsDTO = new TnVsDTO();
        List<TnVsTopSiteDTO> tvts = getTvtsDTO_exp(); //路网过车点位前10
        List<TnVsTopVehicleDTO> tvtvd = getTvtvdDTO_exp(); //
        List<TnVsSiteDTO> tvsd = getTvsdDTO_exp();
        tnVsDTO.setTvsd(tvsd);
        tnVsDTO.setTvtv(tvtvd);
        tnVsDTO.setTvts(tvts);
        dto.setData(tnVsDTO);
        return dto;
    }

    private List<TnVsTopSiteDTO> getTvtsDTO_exp() {
        List<TnVsTopSiteDTO> tvts = new ArrayList<>();
        tvts.add(new TnVsTopSiteDTO("海淀区西二旗", 320));
        tvts.add(new TnVsTopSiteDTO("海淀区上地", 120));
        tvts.add(new TnVsTopSiteDTO("海淀区西直门", 220));
        tvts.add(new TnVsTopSiteDTO("海淀区知春路", 320));
        tvts.add(new TnVsTopSiteDTO("朝阳区东湖渠", 420));
        tvts.add(new TnVsTopSiteDTO("昌平区北七家", 520));
        tvts.add(new TnVsTopSiteDTO("海淀区回龙观", 620));
        tvts.add(new TnVsTopSiteDTO("海淀区龙泽", 720));
        tvts.add(new TnVsTopSiteDTO("海淀区魏公村", 820));
        tvts.add(new TnVsTopSiteDTO("海淀区大钟寺", 320));
        return tvts;
    }

    private List<TnVsTopVehicleDTO> getTvtvdDTO_exp() {
        List<TnVsTopVehicleDTO> tvtv = new ArrayList<>();
        tvtv.add(new TnVsTopVehicleDTO("0",1360));
        tvtv.add(new TnVsTopVehicleDTO("1",2360));
        tvtv.add(new TnVsTopVehicleDTO("2",300));
        tvtv.add(new TnVsTopVehicleDTO("3",500));
        tvtv.add(new TnVsTopVehicleDTO("4",300));
        tvtv.add(new TnVsTopVehicleDTO("5",1234));
        tvtv.add(new TnVsTopVehicleDTO("6",1420));
        tvtv.add(new TnVsTopVehicleDTO("7",1320));
        tvtv.add(new TnVsTopVehicleDTO("8",1905));
        tvtv.add(new TnVsTopVehicleDTO("9",1400));
        tvtv.add(new TnVsTopVehicleDTO("10",203));
        tvtv.add(new TnVsTopVehicleDTO("11",2050));
        tvtv.add(new TnVsTopVehicleDTO("12",3030));
        tvtv.add(new TnVsTopVehicleDTO("13",440));
        tvtv.add(new TnVsTopVehicleDTO("14",1620));
        tvtv.add(new TnVsTopVehicleDTO("15",1802));
        tvtv.add(new TnVsTopVehicleDTO("16",1302));
        tvtv.add(new TnVsTopVehicleDTO("17",1400));
        tvtv.add(new TnVsTopVehicleDTO("18",1300));
        tvtv.add(new TnVsTopVehicleDTO("19",1200));
        tvtv.add(new TnVsTopVehicleDTO("20",1302));
        tvtv.add(new TnVsTopVehicleDTO("21",1400));
        tvtv.add(new TnVsTopVehicleDTO("22",1300));
        tvtv.add(new TnVsTopVehicleDTO("23",2200));
        return tvtv;
    }

    private List<TnVsSiteDTO> getTvsdDTO_exp() {
        List<TnVsSiteDTO> tvtv = new ArrayList<>();
        tvtv.add(new TnVsSiteDTO(105,"上地街道78号",116.1987,40.9365));
        tvtv.add(new TnVsSiteDTO(105,"上地街道78号",116.2987,40.8365));
        tvtv.add(new TnVsSiteDTO(105,"上地街道178号",116.3987,40.7365));
        tvtv.add(new TnVsSiteDTO(105,"上地街道728号",116.4987,40.6365));
        tvtv.add(new TnVsSiteDTO(105,"上地街道278号",116.5987,40.5365));
        return tvtv;
    }
}
