package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.TnVaDeviceDeployDTO;

import java.util.List;
import java.util.Map;

public interface TnVaMapper {
    
    List<TnVaDeviceDeployDTO> getSite();

    Integer getSiteCount();

    List<Map<String, Object>> getDeviceCount();

}
