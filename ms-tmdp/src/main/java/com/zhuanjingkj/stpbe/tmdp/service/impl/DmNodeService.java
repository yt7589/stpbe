package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DmNodeMapper;
import com.zhuanjingkj.stpbe.common.mapper.SmDcMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.dm.AddNodeToNdRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteNodeFromNdRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateNodeRTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDmNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DmNodeService implements IDmNodeService {

    @Autowired
    private DmNodeMapper dmNodeMapper;

    @Autowired
    private SmDcMapper smDcMapper;

    @Override
    public ResultDTO<DbQrsDTO> queryNode_exp(Integer startIndex, Integer amount, Integer direction, String nodeName, String nodeAddr) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<DmNodeDTO> recs = dmNodeMapper.getNodeList(startIndex, amount, nodeName, nodeAddr);
        Integer count = dmNodeMapper.getNodeCount(nodeName, nodeAddr);
        DbQrsDTO data = new DbQrsDTO(count, recs.size(), startIndex, amount, direction, recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> deleteNode_exp(DeleteNodeFromNdRTO rto) {
        System.out.println(rto.getNodeId());
        Integer affectedRows = dmNodeMapper.deleteNode(rto.getNodeId());
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> addNode2Nd_exp(AddNodeToNdRTO rto) {
        System.out.println(rto.getNodeName());
        SmSysInfoDTO smSysInfoDTO = smDcMapper.getSysInfo();
        Integer affectedRows = dmNodeMapper.addNode2Nd(rto, smSysInfoDTO.getCity());
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(rto.getNodeId(), affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> updateNode_exp(UpdateNodeRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<DbDeleteResultDTO>();
        SmSysInfoDTO smSysInfoDTO = smDcMapper.getSysInfo();
        Integer affectedRows = dmNodeMapper.updateNode(rto);
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }
}
