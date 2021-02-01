package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddNodeToNdRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteNodeFromNdRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateNodeRTO;

public interface IDmNodeService {

    ResultDTO<DbQrsDTO> queryNode_exp(Integer startIndex, Integer amount, Integer direction, String nodeName, String nodeAddr);

    ResultDTO<DbDeleteResultDTO> deleteNode_exp(DeleteNodeFromNdRTO rto);

    ResultDTO<DbInsertResultDTO> addNode2Nd_exp(AddNodeToNdRTO rto);

    ResultDTO<DbDeleteResultDTO> updateNode_exp(UpdateNodeRTO rto);
}
