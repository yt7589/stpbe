package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DmNodeMapper;
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

    @Override
    public ResultDTO<DbQrsDTO> queryNode_exp(Integer startIndex, Integer amount, Integer direction, String nodeName, String nodeAddr) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<DmNodeDTO> recs = dmNodeMapper.getNodeList(startIndex, amount, nodeName, nodeAddr);
        Integer count = dmNodeMapper.getNodeCount(nodeName, nodeAddr);
        DbQrsDTO data = new DbQrsDTO(count, recs.size(), startIndex, amount, direction, recs);
//        List<DmNodeDTO> recs = new ArrayList<>();
//        recs.add(new DmNodeDTO(102,"北京市","北京市海淀区西二旗街道19号","海淀>西二旗",116.02,40.23));
//        recs.add(new DmNodeDTO(103,"北京市","北京市海淀区上地街道39号","海淀>上地",116.013,40.33));
//        recs.add(new DmNodeDTO(104,"北京市","北京市海淀区西直门街道29号","海淀>西直门",116.0214,40.43));
//        recs.add(new DmNodeDTO(105,"北京市","北京市海淀区知春路街道109号","海淀>知春路",116.0145,40.53));
//        recs.add(new DmNodeDTO(106,"北京市","北京市朝阳区东湖渠99号","朝阳>东湖渠",116.046,40.630));
//        recs.add(new DmNodeDTO(107,"北京市","北京市昌平区北七家街道21号","昌平>北七家",116.057,40.73));
//        recs.add(new DmNodeDTO(108,"北京市","北京市望京街道59号","朝阳>望京",116.0328,40.83));
//        recs.add(new DmNodeDTO(109,"北京市","北京市海淀区回龙观39号","海淀>回龙观",116.215,40.93));
//        recs.add(new DmNodeDTO(110,"北京市","海淀区上地八街17号位","海淀>上地",116.17,40.103));
//        recs.add(new DmNodeDTO(111,"北京市","海淀区上地八街19号位","海淀>上地",116.87,40.123));
//        recs.add(new DmNodeDTO(112,"北京市","海淀区上地八街21号位","海淀>上地",116.98,40.133));
//        recs.add(new DmNodeDTO(113,"北京市","海淀区上地八街23号位","海淀>上地",116.66,40.1453));
//        data.setRecs(recs);
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
        Integer affectedRows = dmNodeMapper.addNode2Nd(rto);
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(rto.getNodeId(),affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> updateNode_exp(UpdateNodeRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<DbDeleteResultDTO>();
        Integer affectedRows = dmNodeMapper.updateNode(rto);
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }
}
