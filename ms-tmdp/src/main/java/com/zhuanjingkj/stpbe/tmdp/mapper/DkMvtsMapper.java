package com.zhuanjingkj.stpbe.tmdp.mapper;

import com.zhuanjingkj.stpbe.tmdp.dto.DkMvtsDTO;

import java.util.List;

public interface DkMvtsMapper {
    /**
     * 首页违章类型统计
     * @return
     */
    List<DkMvtsDTO> getMvtss();
}
