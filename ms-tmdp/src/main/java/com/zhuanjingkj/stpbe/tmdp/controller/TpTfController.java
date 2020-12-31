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
        @RequestParam(name = "date", required = false) String date,
        @RequestParam(name = "time", required = false) String time
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
        tfs.add(new TpTfSiteDTO(104, "上地七街", 116.098754, 40.1568921));
        tfs.add(new TpTfSiteDTO(104, "上地东路", 116.198754, 40.2568921));
        tfs.add(new TpTfSiteDTO(104, "上地西路", 116.298754, 40.3568921));
        tfs.add(new TpTfSiteDTO(104, "上地二街", 116.398754, 40.4568921));
        tfs.add(new TpTfSiteDTO(104, "上地一街", 116.498754, 40.5568921));
        tfs.add(new TpTfSiteDTO(104, "上地四街", 116.598754, 40.6568921));
        tfs.add(new TpTfSiteDTO(104, "上地五街", 116.698754, 40.7568921));
        tfs.add(new TpTfSiteDTO(104, "上地六街", 116.798754, 40.8568921));
        tfs.add(new TpTfSiteDTO(104, "上地七街", 116.898754, 40.9568921));
        tfs.add(new TpTfSiteDTO(104, "上地八街", 116.998754, 40.0568921));
        tfDTO.setTfst(tfst);
        tfDTO.setTfs(tfs);
        tfDTO.settVehicle(123456789);
        dto.setData(tfDTO);
        return dto;
    }
}
