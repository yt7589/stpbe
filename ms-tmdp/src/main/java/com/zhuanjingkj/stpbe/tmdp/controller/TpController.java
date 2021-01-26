package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfpsDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  Traffic Prognosis
 *  交通预测 =》行车预测
 */
@RestController
@RequestMapping(value = "/tp")
@CrossOrigin(origins = "*")
public class TpController {

    @GetMapping(value = "/queryTrafficPrognosis")
    public ResultDTO<TpDTO> queryTrafficPrognosis(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "startTime", required = false) String startTime,
        @RequestParam(name = "endTime", required = false) String endTime,
        @RequestParam(name = "hphm", required = false) String hphm
    ) {
        return queryTrafficPrognosis_exp();
    }

    private ResultDTO<TpDTO> queryTrafficPrognosis_exp() {
        ResultDTO<TpDTO> dto = new ResultDTO<TpDTO>();
        TpDTO td = new TpDTO();
        List<TpTfpsDTO> tfps = new ArrayList<>();
        tfps.add(new TpTfpsDTO(104, "上地七街", 116.08901, 40.05698));
        tfps.add(new TpTfpsDTO(104, "上地东路", 116.18901, 40.15698));
        tfps.add(new TpTfpsDTO(104, "上地西路", 116.28901, 40.25698));
        tfps.add(new TpTfpsDTO(104, "上地二街", 116.38901, 40.35698));
        tfps.add(new TpTfpsDTO(104, "上地一街", 116.48901, 40.45698));
        td.setTfps(tfps);
        dto.setData(td);
        return dto;
    }
}
