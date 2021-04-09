package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpDTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.TpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  Traffic Prognosis
 *  交通预测 =》行车预测
 */
@RestController
@RequestMapping(value = "/tp")
@CrossOrigin(origins = "*")
public class TpController {

    @Autowired
    private TpService tpService;

    @GetMapping(value = "/queryTrafficPrognosis")
    public ResultDTO<TpDTO> queryTrafficPrognosis(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime,
            @RequestParam(name = "hphm", required = false) String hphm
    ) {
        return queryTrafficPrognosis_exp(startTime, endTime, hphm);
    }

    private ResultDTO<TpDTO> queryTrafficPrognosis_exp(String startTime, String endTime, String hphm) {
        return tpService.queryTrafficPrognosis_exp(startTime, endTime, hphm);
    }
}
