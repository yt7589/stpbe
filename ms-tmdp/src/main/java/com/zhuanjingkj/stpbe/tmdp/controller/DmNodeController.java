package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddNodeToNdRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteNodeFromNdRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateNodeRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DmNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 设备管理 =》 节点管理
 */
@RestController
@RequestMapping(value = "/dm")
@CrossOrigin(origins = "*")
public class DmNodeController {

    @Autowired
    private DmNodeService dmNodeService;

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
        return queryNode_exp(startIndex, amount, direction, nodeName, nodeAddr);
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

    private ResultDTO<DbQrsDTO> queryNode_exp(Integer startIndex, Integer amount, Integer direction, String nodeName, String nodeAddr) {
        return dmNodeService.queryNode_exp(startIndex, amount, direction, nodeName, nodeAddr);
    }

    private ResultDTO<DbDeleteResultDTO> deleteNode_exp(DeleteNodeFromNdRTO rto) {
        return dmNodeService.deleteNode_exp(rto);
    }

    private ResultDTO<DbInsertResultDTO> addNode2Nd_exp(AddNodeToNdRTO rto) {
        return dmNodeService.addNode2Nd_exp(rto);
    }

    private ResultDTO<DbDeleteResultDTO> updateNode_exp(UpdateNodeRTO rto) {
        return dmNodeService.updateNode_exp(rto);
    }
}
