package com.zhuanjingkj.stpbe.common.mapper;

import io.lettuce.core.dynamic.annotation.Param;

public interface DkRtvrMapper {

    public int insertViolation(@Param("tvisJsonId") long tvisJsonId, @Param("vehsIdx") long vehsIdx, @Param("CameraId") long CameraId, @Param("hphm") String hphm, @Param("csysName") String csysName, @Param("clppName") String clppName,
                               @Param("ppcxName") String ppcxName, @Param("cxnkName") String cxnkName, @Param("psfx") String psfx, @Param("clwz") String clwz,
                               @Param("wzlx") String wzlx, @Param("imageHash") String imageHash, @Param("date") String date);

    public String getImageHash(@Param("tvisJsonId") long tvisJsonId, @Param("tblName") String tblName);
}
