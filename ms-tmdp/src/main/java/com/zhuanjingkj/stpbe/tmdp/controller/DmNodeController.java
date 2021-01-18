package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dm.DmNodeDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.AddNodeToNdRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteNodeFromNdRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.UpdateNodeRTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备管理 =》 节点管理
 */
@RestController
@RequestMapping(value = "/dm")
@CrossOrigin(origins = "*")
public class DmNodeController {

    /**
     * 节点列表
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/nd/queryNode")
    public ResultDTO<DbQrsDTO> queryNode(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
        @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction,
        @RequestParam(name = "nodeName", required = false) String nodeName,
        @RequestParam(name = "nodeAddr", required = false) String nodeAddr
    ) {
        return queryNode_exp();
    }

    /**
     * 删除节点
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @DeleteMapping(value = "/nd/deleteNode")
    public ResultDTO<DbDeleteResultDTO> deleteNode(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestBody DeleteNodeFromNdRTO rto
    ) {
        return deleteNode_exp(rto);
    }

    /**
     * 添加节点
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value ="/nd/addNode2Nd")
    public ResultDTO<DbInsertResultDTO> addNode2Nd(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestBody AddNodeToNdRTO rto
    ) {
        return addNode2Nd_exp(rto);
    }

    /**
     * 修改节点信息
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PutMapping(value ="/nd/updateNode")
    public ResultDTO<DbDeleteResultDTO> updateNode(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestBody UpdateNodeRTO rto
    ) {
        return updateNode_exp(rto);
    }

    private ResultDTO<DbQrsDTO> queryNode_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,10,0,10,0,null);
        List<DmNodeDTO> recs = new ArrayList<>();
        recs.add(new DmNodeDTO(102,"北京市","北京市海淀区西二旗街道19号","海淀>西二旗",116.02,40.23));
        recs.add(new DmNodeDTO(103,"北京市","北京市海淀区上地街道39号","海淀>上地",116.013,40.33));
        recs.add(new DmNodeDTO(104,"北京市","北京市海淀区西直门街道29号","海淀>西直门",116.0214,40.43));
        recs.add(new DmNodeDTO(105,"北京市","北京市海淀区知春路街道109号","海淀>知春路",116.0145,40.53));
        recs.add(new DmNodeDTO(106,"北京市","北京市朝阳区东湖渠99号","朝阳>东湖渠",116.046,40.630));
        recs.add(new DmNodeDTO(107,"北京市","北京市昌平区北七家街道21号","昌平>北七家",116.057,40.73));
        recs.add(new DmNodeDTO(108,"北京市","北京市望京街道59号","朝阳>望京",116.0328,40.83));
        recs.add(new DmNodeDTO(109,"北京市","北京市海淀区回龙观39号","海淀>回龙观",116.215,40.93));
        recs.add(new DmNodeDTO(110,"北京市","海淀区上地八街17号位","海淀>上地",116.17,40.103));
        recs.add(new DmNodeDTO(111,"北京市","海淀区上地八街19号位","海淀>上地",116.87,40.123));
        recs.add(new DmNodeDTO(112,"北京市","海淀区上地八街21号位","海淀>上地",116.98,40.133));
        recs.add(new DmNodeDTO(113,"北京市","海淀区上地八街23号位","海淀>上地",116.66,40.1453));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbDeleteResultDTO> deleteNode_exp(DeleteNodeFromNdRTO rto) {
        System.out.println(rto.getNodeId());
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(1);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbInsertResultDTO> addNode2Nd_exp(AddNodeToNdRTO rto) {
        System.out.println(rto.getNodeName());
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(100,1);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbDeleteResultDTO> updateNode_exp(UpdateNodeRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<DbDeleteResultDTO>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(0);
        dto.setData(data);
        return dto;
    }
}
