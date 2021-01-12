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
        recs.add(new DcLrSiteDTO(102,"北京市海淀区西二旗街道19号",116.004004,40.20345));
        recs.add(new DcLrSiteDTO(102,"北京市海淀区上地街道39号",116.007004,40.20445));
        recs.add(new DcLrSiteDTO(102,"北京市海淀区西直门街道29号",116.009004,40.20845));
        recs.add(new DcLrSiteDTO(102,"北京市海淀区知春路街道109号",116.014004,40.20945));
        recs.add(new DcLrSiteDTO(102,"北京市朝阳区东湖区99号",116.034004,40.20745));
        recs.add(new DcLrSiteDTO(102,"北京市昌平区北七家街道21号",116.084004,40.21045));
        recs.add(new DcLrSiteDTO(102,"北京市望京街道59号",116.094004,40.21345));
        recs.add(new DcLrSiteDTO(102,"北京市海淀区回龙观39号",116.044004,40.23345));
        data.setLrSite(recs);
        dto.setData(data);
        return dto;
    }
}
