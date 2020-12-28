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
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version
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
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 320));
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 120));
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 220));
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 320));
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 420));
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 520));
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 620));
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 720));
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 820));
        tvts.add(new TnVsTopSiteDTO("上地三街105号", 320));
        return tvts;
    }

    private List<TnVsTopVehicleDTO> getTvtvdDTO_exp() {
        List<TnVsTopVehicleDTO> tvtv = new ArrayList<>();
        tvtv.add(new TnVsTopVehicleDTO("0",360));
        tvtv.add(new TnVsTopVehicleDTO("1",360));
        tvtv.add(new TnVsTopVehicleDTO("2",360));
        tvtv.add(new TnVsTopVehicleDTO("3",360));
        tvtv.add(new TnVsTopVehicleDTO("4",360));
        tvtv.add(new TnVsTopVehicleDTO("5",360));
        tvtv.add(new TnVsTopVehicleDTO("6",360));
        tvtv.add(new TnVsTopVehicleDTO("7",360));
        tvtv.add(new TnVsTopVehicleDTO("8",360));
        tvtv.add(new TnVsTopVehicleDTO("9",360));
        tvtv.add(new TnVsTopVehicleDTO("10",360));
        tvtv.add(new TnVsTopVehicleDTO("11",360));
        tvtv.add(new TnVsTopVehicleDTO("12",360));
        tvtv.add(new TnVsTopVehicleDTO("13",360));
        tvtv.add(new TnVsTopVehicleDTO("14",360));
        tvtv.add(new TnVsTopVehicleDTO("15",360));
        tvtv.add(new TnVsTopVehicleDTO("16",360));
        tvtv.add(new TnVsTopVehicleDTO("17",360));
        tvtv.add(new TnVsTopVehicleDTO("18",360));
        tvtv.add(new TnVsTopVehicleDTO("19",360));
        tvtv.add(new TnVsTopVehicleDTO("20",360));
        tvtv.add(new TnVsTopVehicleDTO("21",360));
        tvtv.add(new TnVsTopVehicleDTO("22",360));
        tvtv.add(new TnVsTopVehicleDTO("23",360));
        return tvtv;
    }

    private List<TnVsSiteDTO> getTvsdDTO_exp() {
        List<TnVsSiteDTO> tvtv = new ArrayList<>();
        tvtv.add(new TnVsSiteDTO(105,"上地街道78号",89.987,59.365));
        tvtv.add(new TnVsSiteDTO(105,"上地街道78号",95.987,63.365));
        tvtv.add(new TnVsSiteDTO(105,"上地街道178号",96.987,74.365));
        tvtv.add(new TnVsSiteDTO(105,"上地街道728号",100.987,89.365));
        tvtv.add(new TnVsSiteDTO(105,"上地街道278号",105.987,109.365));
        return tvtv;
    }
}
