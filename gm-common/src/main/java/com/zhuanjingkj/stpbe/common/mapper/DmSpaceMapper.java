package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DmAmAreaDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddAreaToSpaceRTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DmSpaceMapper {

    List<DmAmAreaDTO> getSpaceArea(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount);

    Integer getSpaceAreaCount();

    Map<String, Object> getSpaceAreaInfo(@Param("parentId") String parentId);

    String getSpaceMaxCode(@Param("code") String code);

    Integer insertDmSpace(@Param("rto") AddAreaToSpaceRTO rto, @Param("newcode") String newcode, @Param("level") Integer level);

    Integer updateAreaInfo(@Param("areaId") long areaId, @Param("areaName") String areaName);

    Integer deleteSpaceArea(@Param("areaId") long areaId);
}
