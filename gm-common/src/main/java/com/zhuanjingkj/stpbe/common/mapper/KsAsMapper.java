package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.AreaDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface KsAsMapper {

    Integer getAreaCount(@Param("areaName")String areaName, @Param("type")Integer type);

    List<AreaDTO> getKsArea(@Param("areaName")String areaName, @Param("startIndex")Integer startIndex, @Param("amount")Integer amount, @Param("type")Integer type);

    Integer deleteArea(@Param("areaId")long areaId);

    Integer addAreas(@Param("areas") List<Integer> areas);

    List<Map<String, Object>> getKsAreaCode();
}
