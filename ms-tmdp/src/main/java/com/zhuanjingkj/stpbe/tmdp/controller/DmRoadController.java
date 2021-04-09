package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddRoadSectionToRsRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.DeleteRoadSectionFromRsRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DmRoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 设备管理 =》路段管理
 */
@RestController
@RequestMapping(value = "/dm")
@CrossOrigin(origins = "*")
public class DmRoadController {

    @Autowired
    private DmRoadService dmRoadService;

    /**
     * 路段设备列表
     * @param platform
     * @param version
     * @param roadName
     * @return
     */
    @GetMapping(value = "/rd/queryRoadSection")
    public ResultDTO<DbQrsDTO> queryRoad(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestParam(name = "roadName", required = false) String roadName,
            @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
            @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
            @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction
    ) {
        return queryRoad_exp(startIndex, amount, direction, roadName);
    }

    /**
     * 添加路段设备
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value = "/rd/addRoadSection")
    public ResultDTO<DbInsertResultDTO> addRoadSection(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestBody AddRoadSectionToRsRTO rto
    ) {
        return addRoadSection_exp(rto);
    }

    /**
     * 删除路段设备
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @DeleteMapping(value = "/rd/deleteRoadSection")
    public ResultDTO<DbDeleteResultDTO> deleteRoadSection(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestBody DeleteRoadSectionFromRsRTO rto
    ) {
        return deleteRoadSection_exp(rto);
    }

    private ResultDTO<DbQrsDTO> queryRoad_exp(Integer startIndex, Integer amount, Integer direction, String roadName) {
        return dmRoadService.queryRoad(startIndex, amount, direction, roadName);
    }

    private ResultDTO<DbInsertResultDTO> addRoadSection_exp(AddRoadSectionToRsRTO rto) {
        return dmRoadService.addRoadSection_exp(rto);
    }

    private ResultDTO<DbDeleteResultDTO> deleteRoadSection_exp(DeleteRoadSectionFromRsRTO rto) {
        return dmRoadService.deleteRoadSection_exp(rto);
    }
}
