package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DmNodeDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddNodeToNdRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateNodeRTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmNodeMapper {
    List<DmNodeDTO> getNodeList(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount,
                                @Param("nodeName") String nodeName, @Param("nodeAddr") String nodeAddr);

    Integer getNodeCount(@Param("nodeName") String nodeName, @Param("nodeAddr") String nodeAddr);

    Integer deleteNode(@Param("nodeId") long nodeId);

    Integer addNode2Nd(@Param("rto") AddNodeToNdRTO rto, @Param("city") String city);

    Integer updateNode(@Param("rto") UpdateNodeRTO rto);
}
