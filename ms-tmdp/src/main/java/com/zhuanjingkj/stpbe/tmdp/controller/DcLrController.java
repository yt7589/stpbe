package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcLrDTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DcLrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Data Center =》 Locus Replay
 * 数据中心 =》 轨迹回放
 */
@RestController
@RequestMapping(value = "/dc")
@CrossOrigin(origins = "*")
public class DcLrController {

    @Autowired
    private DcLrService dcLrService;

    /**
     * 轨迹回放查询
     * @param platform
     * @param version
     * @param startTime
     * @param endTime
     * @param hphm
     * @return
     */
    @GetMapping(value = "/lr/queryLocusReplay")
    public ResultDTO<DcLrDTO> queryLocusReplay(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime,
            @RequestParam(name = "hphm", required = false) String hphm
    ) {
        return queryLocusReplay_exp(startTime, endTime, hphm);
    }

    private ResultDTO<DcLrDTO> queryLocusReplay_exp(String startTime, String endTime, String hphm) {
        return dcLrService.queryLocusReplay_exp(startTime, endTime, hphm);
    }
}
