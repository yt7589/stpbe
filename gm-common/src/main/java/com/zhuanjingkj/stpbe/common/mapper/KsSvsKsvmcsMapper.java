package com.zhuanjingkj.stpbe.common.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface KsSvsKsvmcsMapper {
    List<Map<String, Object>> getVehicleType();
}
