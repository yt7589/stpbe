package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddAreaToSpaceRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteAreaFromSpaceRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DmSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *设备管理 =》空间管理
 */
@RestController
@RequestMapping(value = "/dm")
@CrossOrigin(origins = "*")
public class DmSpaceController {

    @Autowired
    private DmSpaceService dmSpaceService;

    /**
     * 空间管理列表
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/sm/queryArea")
    public ResultDTO<DbQrsDTO> queryArea (
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
        @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction
    ) {
        return queryArea_exp(startIndex, amount, direction);
    }

    /**
     * 添加下一级区域
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value = "/sm/addArea2Space")
    public ResultDTO<DbInsertResultDTO> addAreaToSpace (
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestBody AddAreaToSpaceRTO rto
    ) {
        return addAreaToSpace_exp(rto);
    }

    /**
     * 修改区域名称
     * @param platform
     * @param version
     * @param areaId
     * @return
     */
    @PutMapping(value = "/sm/updateArea")
    public ResultDTO<DbDeleteResultDTO> updateArea (
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "areaId", required = false) long areaId,
        @RequestParam(name = "areaName", required = false) String areaName
    ) {
        return updateArea_exp(areaId, areaName);
    }

    /**
     * 删除区域
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @DeleteMapping(value = "/sm/deleteAreaFromSpace")
    public ResultDTO<DbDeleteResultDTO> deleteAreaFromSpace(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestBody DeleteAreaFromSpaceRTO rto
    ) {
        return deleteAreaFromSpace_exp(rto);
    }

    private ResultDTO<DbQrsDTO> queryArea_exp(Integer startIndex, Integer amount, Integer direction) {
        return dmSpaceService.queryArea_exp(startIndex, amount, direction);
    }

    private ResultDTO<DbInsertResultDTO> addAreaToSpace_exp(AddAreaToSpaceRTO rto) {
        return dmSpaceService.addAreaToSpace_exp(rto);
    }

    private ResultDTO<DbDeleteResultDTO> updateArea_exp(long areaId, String areaName) {
        return dmSpaceService.updateArea_exp(areaId, areaName);
    }

    private ResultDTO<DbDeleteResultDTO> deleteAreaFromSpace_exp(DeleteAreaFromSpaceRTO rto) {
        return dmSpaceService.deleteAreaFromSpace_exp(rto);
    }
}
