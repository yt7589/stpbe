package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.TnVaDeviceDeployDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVaSdInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TnVaMapper {
    
    List<TnVaDeviceDeployDTO> getSite();

    Integer getSiteCount();

    List<Map<String, Object>> getDeviceCount();

    List<TnVaSdInfoDTO> getSiteInfo(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount, @Param("siteId") long siteId);

    List<Map<String, Object>> getDevCount(@Param("siteId") long siteId);
}
