package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DmAmAreaDTO;
import com.zhuanjingkj.stpbe.data.dto.SmSiteAddrDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddAreaToSpaceRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateSpaceAreaRTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DmSpaceMapper {

    List<DmAmAreaDTO> getSpaceArea(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount);

    Integer getSpaceAreaCount();

    Map<String, Object> getSpaceAreaInfo(@Param("parentId") Integer parentId);

    String getSpaceMaxCode(@Param("code") String code);

    Integer insertDmSpace(@Param("rto") AddAreaToSpaceRTO rto, @Param("newcode") String newcode, @Param("level") Integer level);

    Integer updateAreaInfo(@Param("rto") UpdateSpaceAreaRTO rto);

    Integer deleteSpaceArea(@Param("areaId") long areaId);

    List<SmSiteAddrDTO> querySiteAddr();
}
