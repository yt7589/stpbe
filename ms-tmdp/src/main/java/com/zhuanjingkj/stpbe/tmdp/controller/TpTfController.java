package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfDTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.TptfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  Traffic Prognosis =》 Traffic Forecast
 * 交通预测 =》 流量预测
 */
@RestController
@RequestMapping("/tp")
@CrossOrigin(origins = "*")
public class TpTfController {

    @Autowired
    private TptfService tptfService;

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
        return queryTrafficForecast_exp(date, time);
    }

    private ResultDTO<TpTfDTO> queryTrafficForecast_exp(String date, String time) {
        return tptfService.queryTrafficForecast_exp(date, time);
    }
}
