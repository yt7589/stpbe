package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DkMvtsDTO;

import java.util.List;

public interface DkMvtsMapper {
    /**
     * 首页违章类型统计
     * @return
     */
    List<DkMvtsDTO> getMvtss();
}
