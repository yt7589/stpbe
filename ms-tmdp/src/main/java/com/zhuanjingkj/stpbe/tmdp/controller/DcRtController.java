package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DcRtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data center =》 Data Report
 * 数据中心 =》 数据报告
 */
@RestController
@RequestMapping(value = "/dc")
@CrossOrigin(origins = "*")
public class DcRtController {

    @Autowired
    private DcRtService dcRtService;

    /**
     * 数据报告
     * @param platform
     * @param version
     * @param tp
     * @return
     */
    @GetMapping(value = "/rt/queryDataReport")
    public ResultDTO<DcRtDTO> queryDataReport(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestParam(name = "tp", defaultValue = "today", required = false) String tp
    ) {
        return queryDataReport_exp(tp);
    }

    private ResultDTO<DcRtDTO> queryDataReport_exp(String tp) {
        return dcRtService.queryDataReport_exp(tp);
    }
}
