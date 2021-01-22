package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dm.DmAmAreaDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.AddAreaToSpaceRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteAreaFromSpaceRTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *设备管理 =》空间管理
 */
@RestController
@RequestMapping(value = "/dm")
@CrossOrigin(origins = "*")
public class DmSpaceController {

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
        return queryArea_exp();
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
        @RequestParam(name = "areaId", required = false) String areaId,
        @RequestParam(name = "areaName", required = false) String areaName
    ) {
        return updateArea_exp(areaId);
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

    private ResultDTO<DbQrsDTO> queryArea_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,20,0,20,0,null);
        List<DmAmAreaDTO> recs = new ArrayList<>();
        recs.add(new DmAmAreaDTO(103,"圆明园西路","143456","海淀区"));
        recs.add(new DmAmAreaDTO(104,"朝阳区","153456","北京市"));
        recs.add(new DmAmAreaDTO(103,"上地","163456","海淀区"));
        recs.add(new DmAmAreaDTO(104,"海淀区","173456","海淀区"));
        recs.add(new DmAmAreaDTO(103,"西直门","183456","海淀区"));
        recs.add(new DmAmAreaDTO(104,"东直门","193456","朝阳区"));
        recs.add(new DmAmAreaDTO(103,"惠新西街","223456","海淀区"));
        recs.add(new DmAmAreaDTO(104,"石景山区","323456","北京市"));
        recs.add(new DmAmAreaDTO(103,"门头沟区","423456","北京市"));
        recs.add(new DmAmAreaDTO(104,"朝阳区","523456","北京市"));
        recs.add(new DmAmAreaDTO(103,"通州区","623456","北京市"));
        recs.add(new DmAmAreaDTO(104,"朝阳区","723456","北京市"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbInsertResultDTO> addAreaToSpace_exp(AddAreaToSpaceRTO rto) {
        System.out.println(rto.getAreaName());
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(101,1);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbDeleteResultDTO> updateArea_exp(String areaId) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(1);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbDeleteResultDTO> deleteAreaFromSpace_exp(DeleteAreaFromSpaceRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(0);
        dto.setData(data);
        return dto;
    }
}
