package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DkMvtsDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DkMvtsMapper {
    /**
     * 首页违章类型统计
     * @return
     */
    List<DkMvtsDTO> getMvtss(@Param("date")String date);
}
