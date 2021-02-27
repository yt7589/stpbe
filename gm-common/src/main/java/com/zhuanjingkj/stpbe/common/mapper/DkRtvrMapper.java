package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DkRtvrDTO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DkRtvrMapper {

    public int insertViolation(@Param("tvisJsonId") long tvisJsonId, @Param("vehsIdx") long vehsIdx, @Param("cameraId") long cameraId, @Param("hphm") String hphm,
                               @Param("csysName") String csysName, @Param("clppName") String clppName, @Param("ppcxName") String ppcxName,
                               @Param("cxnkName") String cxnkName, @Param("psfx") String psfx, @Param("clwz") String clwz, @Param("wzlx") String wzlx,
                               @Param("vtype") String vtype, /**@Param("imageHash") String imageHash, @Param("jsonHash") String jsonHash,*/Integer category, @Param("tblName") String tblName,
                               @Param("date") String date);

    public Map<String, Object> getImageHash(@Param("tvisJsonId") long tvisJsonId, @Param("tblName") String tblName);

    /**
     * 查询违章记录前2条
     * @return
     */
    List<DkRtvrDTO> getTop2Violation();
}
