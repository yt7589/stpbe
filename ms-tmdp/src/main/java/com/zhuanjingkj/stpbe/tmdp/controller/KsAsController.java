package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.AddAreasToKeyAreasRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.DeleteAreaFromKeyAreasRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.KsAsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 * Key Supervision => Area Supervision 重点监管=》区域监管
 */
@RestController
@RequestMapping("/ks")
@CrossOrigin(origins = "*")
public class KsAsController {

    private static final Logger log = LoggerFactory.getLogger(KsAsController.class);

    @Autowired
    private KsAsService ksAsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("as/queryKeyAreas")
    public ResultDTO<DbQrsDTO> queryKeyAreas(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "areaName", required = false) String areaName,
            @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
            @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
            @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction
    ) {
        log.info("areaName:" + areaName + "; startIndex:" + startIndex + "; amount:" + amount + "; direction:" + direction);
        return queryKeyAreas_exp(areaName, startIndex, amount, direction, 1);
    }

    @GetMapping("as/queryAreas")
    public ResultDTO<DbQrsDTO> queryAreas(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "areaName", required = false) String areaName,
            @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
            @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
            @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction
    ) {
        log.info("areaName:" + areaName + "; startIndex:" + startIndex + "; amount:" + amount + "; direction" + direction);
        return queryKeyAreas_exp(areaName, startIndex, amount, direction, 0);
    }

    @PostMapping("as/addAreasToKeyAreas")
    public ResultDTO<DbInsertResultDTO> addAreasToKeyAreas(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestBody AddAreasToKeyAreasRTO rto) {
        return addAreasToKeyAreas_exp(rto);
    }

    @DeleteMapping("as/deleteAreaFromKeyAreas")
    public ResultDTO<DbDeleteResultDTO> deleteAreaFromKeyAreas(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestBody DeleteAreaFromKeyAreasRTO rto) {
        log.info("delete area:" + rto.getAreaId());
        return deleteAreaFromKeyAreas_exp(rto);
    }

    private ResultDTO<DbQrsDTO> queryKeyAreas_exp(String areaName, Integer startIndex, Integer amount, Integer direction, Integer type) {
        return ksAsService.queryKeyAreas_exp(areaName, startIndex, amount, direction, type);
    }

    private ResultDTO<DbInsertResultDTO> addAreasToKeyAreas_exp(AddAreasToKeyAreasRTO rto) {
        return ksAsService.addAreasToKeyAreas_exp(rto);
    }

    private ResultDTO<DbDeleteResultDTO> deleteAreaFromKeyAreas_exp(DeleteAreaFromKeyAreasRTO rto) {
        return ksAsService.deleteAreaFromKeyAreas_exp(rto);
    }
}































