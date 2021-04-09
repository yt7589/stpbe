package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.KsLpsLalpDTO;
import com.zhuanjingkj.stpbe.data.dto.KsLpsSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.KsLpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Key Supervision => License plate supervision
 * 重点监管=》牌照异常
 */
@RestController
@RequestMapping("/ks")
@CrossOrigin(origins = "*")
public class KsLpsController {

    @Autowired
    private KsLpsService ksLpsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/lps/queryAbnormalLicensePlate")
    public ResultDTO<KsLpsDTO> queryAbnormalLicensePlate(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version
    ) {
        ResultDTO<KsLpsDTO> dto = new ResultDTO<>();
        KsLpsDTO data = new KsLpsDTO();
        List<KsLpsTimeDTO> timeDTO = getTimeAbnormalLicensePlate();
        List<KsLpsAreaDTO> areaDTO = getAreaAbnormalLicensePlate();
        List<KsLpsSiteDTO> siteDTO = getSiteAbnormalLicensePlate();
        List<KsLpsLalpDTO> lalpDTO = getKsLpsLalp();
        data.setWpCount(Integer.parseInt(redisTemplate.opsForValue().get("ks_lps_wp") == null ? "0" : ""+ redisTemplate.opsForValue().get("ks_lps_wp")));
        data.setTpCount(Integer.parseInt(redisTemplate.opsForValue().get("ks_lps_tp") == null ? "0" : ""+ redisTemplate.opsForValue().get("ks_lps_tp")));
        data.setJpCount(Integer.parseInt(redisTemplate.opsForValue().get("ks_lps_jp") == null ? "0" : ""+ redisTemplate.opsForValue().get("ks_lps_jp")));
        data.setHpzdCount(Integer.parseInt(redisTemplate.opsForValue().get("ks_lps_hpzd") == null ? "0" : ""+ redisTemplate.opsForValue().get("ks_lps_hpzd")));
        data.setLalp(lalpDTO);
        data.setArea(areaDTO);
        data.setTime(timeDTO);
        data.setSite(siteDTO);
        dto.setData(data);
        return dto;
    }

    /**
     * 时段异常牌照统计
     * @return
     */
    private List<KsLpsTimeDTO> getTimeAbnormalLicensePlate() {
        return ksLpsService.getTimeAbnormalLicensePlate();
    }

    /**
     * 区域异常牌照统计
     * @return
     */
    private List<KsLpsAreaDTO> getAreaAbnormalLicensePlate() {
        return ksLpsService.getAreaAbnormalLicensePlate();
    }

    /**
     * 地图点位异常牌照
     * @return
     */
    private List<KsLpsSiteDTO> getSiteAbnormalLicensePlate() {
        return ksLpsService.getSiteAbnormalLicensePlate();
    }

    /**
     * 最新异常车牌记录
     * @return
     */
    private List<KsLpsLalpDTO> getKsLpsLalp() {
        return ksLpsService.getKsLpsLalp();
    }

}
