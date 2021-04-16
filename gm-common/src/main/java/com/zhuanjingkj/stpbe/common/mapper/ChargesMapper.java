package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.ZjcChargesDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.AddCharges;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChargesMapper {

    List<ZjcChargesDTO> getCharges(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount,
                                   @Param("direction") Integer direction, @Param("charges_id")Integer charges_id);

    Integer addCharges(@Param("rto") AddCharges rto);

    Integer uptCharges(@Param("rto") AddCharges rto);

    Integer getChargesCount(@Param("charges_id") Integer charges_id);
}
