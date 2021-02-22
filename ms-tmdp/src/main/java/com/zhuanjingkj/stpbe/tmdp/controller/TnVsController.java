package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVsVehicleDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsSiteDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsTopSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVsTopVehicleDTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.TnVsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Traffic Network => Vehicle statistics
 * 路网实况 =》 路网车辆统计
 */
@RestController
@RequestMapping("/tn")
@CrossOrigin(origins = "*")
public class TnVsController {

    @Autowired
    private TnVsService tnVsService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 路网过车统计
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/vs/queryVehicleStatistics")
    public ResultDTO<TnVsDTO> queryEquipment(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        ResultDTO<TnVsDTO> dto = new ResultDTO<TnVsDTO>();
        TnVsDTO tnVsDTO = new TnVsDTO();
        List<TnVsTopSiteDTO> tvts = getTvtsDTO_exp(); //路网过车点位前10
        TnVsVehicleDTO tvtvd = getTvtvdDTO_exp(); //车辆数量走势图
//        List<TnVsSiteDTO> tvsd = getTvsdDTO_exp();
//        tnVsDTO.setTvsd(tvsd);
        tnVsDTO.setTvtv(tvtvd);
        tnVsDTO.setTvts(tvts);
        dto.setData(tnVsDTO);
        return dto;
    }

    private List<TnVsTopSiteDTO> getTvtsDTO_exp() {
        return tnVsService.getTvtsDTO_exp();
    }

    private TnVsVehicleDTO getTvtvdDTO_exp() {
        return tnVsService.getTvtvdDTO_exp();
    }
}
