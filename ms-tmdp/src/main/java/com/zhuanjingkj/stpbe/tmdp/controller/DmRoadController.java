package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dm.DmRoadSectionDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.AddRoadSectionToRsRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteRoadSectionFromRsRTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备管理 =》路段管理
 */
@RestController
@RequestMapping(value = "/dm")
@CrossOrigin(origins = "*")
public class DmRoadController {

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
        @RequestParam(name = "roadName", required = false) String roadName
    ) {
        return queryRoad_exp();
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

    private ResultDTO<DbQrsDTO> queryRoad_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,10,0,10,0,null);
        List<DmRoadSectionDTO> recs = new ArrayList<>();
        recs.add(new DmRoadSectionDTO(101,"北京市海淀区西二旗街道19号","海淀区>西二旗",116.23,40.143));
        recs.add(new DmRoadSectionDTO(102,"北京市海淀区上地街道39号","海淀区>上地",116.14,40.414));
        recs.add(new DmRoadSectionDTO(103,"北京市海淀区西直门街道29号","海淀区>西直门",116.35,40.415));
        recs.add(new DmRoadSectionDTO(104,"北京市海淀区知春路街道109号","海淀区>知春路",116.16,40.212));
        recs.add(new DmRoadSectionDTO(105,"北京市朝阳区东湖区99号","朝阳区>东湖渠",116.47,40.324));
        recs.add(new DmRoadSectionDTO(106,"北京市昌平区北七家街道21号","昌平区>北七家",116.148,40.442));
        recs.add(new DmRoadSectionDTO(107,"北京市望京街道59号","朝阳>望京",116.443,40.3112));
        recs.add(new DmRoadSectionDTO(108,"北京市海淀区回龙观39号","海淀区>回龙观",116.229,40.624));
        recs.add(new DmRoadSectionDTO(109,"海淀区上地八街12号","海淀区>上地",116.441,40.522));
        recs.add(new DmRoadSectionDTO(100,"北京市海淀区龙泽23号","海淀区>龙泽",116.223,40.7222));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbInsertResultDTO> addRoadSection_exp(AddRoadSectionToRsRTO rto) {
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(10,1);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbDeleteResultDTO> deleteRoadSection_exp(DeleteRoadSectionFromRsRTO rto) {
        System.out.println(rto.getRssId());
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(1);
        dto.setData(data);
        return dto;
    }
}
