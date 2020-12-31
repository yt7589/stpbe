package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcLrDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcLrSiteDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Center =》 Locus Replay
 * 数据中心 =》 轨迹回放
 */
@RestController
@RequestMapping(value = "/dc")
@CrossOrigin(origins = "*")
public class DcLrController {

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
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "startTime", required = false) String startTime,
        @RequestParam(name = "endTime", required = false) String endTime,
        @RequestParam(name = "hphm", required = false) String hphm
    ) {
        return queryLocusReplay_exp();
    }

    private ResultDTO<DcLrDTO> queryLocusReplay_exp() {
        ResultDTO<DcLrDTO> dto = new ResultDTO<>();
        DcLrDTO data = new DcLrDTO();
        List<DcLrSiteDTO> recs = new ArrayList<>();
        recs.add(new DcLrSiteDTO(102,"上地三街123号",89.235,45.236));
        recs.add(new DcLrSiteDTO(102,"上地三街123号",89.235,45.236));
        recs.add(new DcLrSiteDTO(102,"上地三街123号",89.235,45.236));
        recs.add(new DcLrSiteDTO(102,"上地三街123号",89.235,45.236));
        recs.add(new DcLrSiteDTO(102,"上地三街123号",89.235,45.236));
        recs.add(new DcLrSiteDTO(102,"上地三街123号",89.235,45.236));
        recs.add(new DcLrSiteDTO(102,"上地三街123号",89.235,45.236));
        recs.add(new DcLrSiteDTO(102,"上地三街123号",89.235,45.236));
        data.setLrSite(recs);
        dto.setData(data);
        return dto;
    }
}
