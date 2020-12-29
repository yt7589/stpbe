package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfSiteDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfStDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  Traffic Prognosis =》 Traffic Forecast
 * 交通预测 =》 流量预测
 */
@RestController
@RequestMapping("/tp")
@CrossOrigin(origins = "*")
public class TpTfController {

    /**
     * 流量预测查询
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/tf/queryTrafficForecast")
    public ResultDTO<TpTfDTO> queryTrafficForecast(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "date") String date,
        @RequestParam(name = "time") String time
    ) {
        return queryTrafficForecast_exp();
    }

    private ResultDTO<TpTfDTO> queryTrafficForecast_exp() {
        ResultDTO<TpTfDTO> dto = new ResultDTO<TpTfDTO>();
        TpTfDTO tfDTO = new TpTfDTO();
        List<TpTfStDTO> tfst = new ArrayList<>();
        tfst.add(new TpTfStDTO("上地三街129号", 203));
        tfst.add(new TpTfStDTO("上地四街129号", 303));
        tfst.add(new TpTfStDTO("上地五街129号", 403));
        tfst.add(new TpTfStDTO("上地六街129号", 503));
        tfst.add(new TpTfStDTO("上地七街129号", 603));
        tfst.add(new TpTfStDTO("上地八街129号", 703));
        tfst.add(new TpTfStDTO("上地九街129号", 803));
        tfst.add(new TpTfStDTO("上地十街129号", 903));
        tfst.add(new TpTfStDTO("上地二街129号", 100));
        tfst.add(new TpTfStDTO("上地西路129号", 110));
        List<TpTfSiteDTO> tfs = new ArrayList<>();
        tfs.add(new TpTfSiteDTO(104, "上地七街", 100, 70));
        tfs.add(new TpTfSiteDTO(104, "上地东路", 90, 80));
        tfs.add(new TpTfSiteDTO(104, "上地西路", 80, 90));
        tfs.add(new TpTfSiteDTO(104, "上地二街", 70, 10));
        tfs.add(new TpTfSiteDTO(104, "上地一街", 60, 20));
        tfs.add(new TpTfSiteDTO(104, "上地四街", 50, 30));
        tfs.add(new TpTfSiteDTO(104, "上地五街", 40, 40));
        tfs.add(new TpTfSiteDTO(104, "上地六街", 30, 50));
        tfs.add(new TpTfSiteDTO(104, "上地七街", 20, 60));
        tfs.add(new TpTfSiteDTO(104, "上地八街", 10, 70));
        tfDTO.setTfst(tfst);
        tfDTO.setTfs(tfs);
        tfDTO.settVehicle(123456789);
        dto.setData(tfDTO);
        return dto;
    }
}
