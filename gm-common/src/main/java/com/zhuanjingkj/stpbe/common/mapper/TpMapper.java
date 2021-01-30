package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.TpTfpsDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TpMapper {
    List<TpTfpsDTO> getTrafficPrognosis(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("hphm") String hphm);
}
