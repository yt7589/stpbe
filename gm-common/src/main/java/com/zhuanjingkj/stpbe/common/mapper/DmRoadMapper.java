package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.rto.dm.AddRoadSectionToRsRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.DeleteRoadSectionFromRsRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.DmRoadSectionDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmRoadMapper {

    List<DmRoadSectionDTO> getRoad(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount, @Param("roadName") String roadName);

    Integer getRoadCount(@Param("roadName") String roadName);

    Integer addRoadSection(@Param("rto") AddRoadSectionToRsRTO rto);

    Integer deleteRoadSection(@Param("rto") DeleteRoadSectionFromRsRTO rto);
}
