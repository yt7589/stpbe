package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfSiteDTO;
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
        tfps.add(new TpTfpsDTO(104, "上地七街", 100, 70));
        tfps.add(new TpTfpsDTO(104, "上地东路", 90, 80));
        tfps.add(new TpTfpsDTO(104, "上地西路", 80, 90));
        tfps.add(new TpTfpsDTO(104, "上地二街", 70, 10));
        tfps.add(new TpTfpsDTO(104, "上地一街", 60, 20));
        td.setTfps(tfps);
        dto.setData(td);
        return dto;
    }
}
