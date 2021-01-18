package com.zhuanjingkj.stpbe.common.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface KsSvsHtfsMapper {

    List<String> getVehicleType();

    Integer getDevice();

    Integer getWarnNum(@Param("localDate")LocalDate localDate);

    List<String> getKaCameraCode();
}
