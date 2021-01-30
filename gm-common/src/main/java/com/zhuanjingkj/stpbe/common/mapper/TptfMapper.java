package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.TpTfSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.TpTfStDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TptfMapper {
    Integer getVehicle4TimeCount(@Param("queryTime") String queryTime);

    List<TpTfStDTO> getVehicle4TimeSite(@Param("queryTime") String queryTime);

    List<TpTfSiteDTO> getVehicle4TimeMap(@Param("queryTime") String queryTime);
}
