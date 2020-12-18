package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.AreaDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.AddAreasToKeyAreasRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.DeleteAreaFromKeyAreasRTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Key Supervision => Area Supervision 重点监管=》区域监管
 */
@RestController
@RequestMapping("/ks")
@CrossOrigin(origins = "*")
public class KsAsController {
    @GetMapping("as/queryKeyAreas")
    public ResultDTO<DbQrsDTO> queryKeyAreas(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "areaName", required = false) String areaName,
            @RequestParam(name = "startIndex", required = false) Integer startIndex,
            @RequestParam(name = "amount", required = false) Integer amount,
            @RequestParam(name = "driection", required = false) Integer direction
    ) {
        return queryKeyAreas_exp();
    }

    @GetMapping("as/queryAreas")
    public ResultDTO<DbQrsDTO> queryAreas(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "areaName", required = false) String areaName,
            @RequestParam(name = "startIndex", required = false) Integer startIndex,
            @RequestParam(name = "amount", required = false) Integer amount,
            @RequestParam(name = "driection", required = false) Integer direction
    ) {
        return queryKeyAreas_exp();
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
        return deleteAreaFromKeyAreas_exp(rto);
    }

    private ResultDTO<DbQrsDTO> queryKeyAreas_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100, 5, 0, 10, 0,null);
        List<AreaDTO> recs = new ArrayList<>();
        recs.add(new AreaDTO(101, "上地", 0, 3, "1_1_1"));
        recs.add(new AreaDTO(102, "五道口", 1, 3, "1_1_2"));
        recs.add(new AreaDTO(103, "东直门", 1, 3, "1_1_2"));
        recs.add(new AreaDTO(104, "动物园", 1, 3, "1_1_2"));
        recs.add(new AreaDTO(105, "新街口", 1, 3, "1_1_2"));
        recs.add(new AreaDTO(106, "六里桥", 1, 3, "1_1_2"));
        recs.add(new AreaDTO(107, "车道沟", 1, 3, "1_1_2"));
        recs.add(new AreaDTO(108, "朝阳门", 1, 3, "1_1_2"));
        recs.add(new AreaDTO(109, "人大双安", 1, 3, "1_1_2"));
        recs.add(new AreaDTO(110, "联想桥", 1, 3, "1_1_2"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbInsertResultDTO> addAreasToKeyAreas_exp(AddAreasToKeyAreasRTO rto) {
        List<Integer> areas = rto.getAreas();
        for (int area : areas) {
            System.out.println("add area: " + area + "!");
        }
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(108, 1);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbDeleteResultDTO> deleteAreaFromKeyAreas_exp(DeleteAreaFromKeyAreasRTO rto) {
        System.out.println("delete area: " + rto.getAreaId() + "!");
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(1);
        dto.setData(data);
        return dto;
    }
}































