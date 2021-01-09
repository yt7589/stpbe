package com.zhuanjingkj.stpbe.tmdp.mapper;

import com.zhuanjingkj.stpbe.tmdp.dto.DkMvtsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkRtvrDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DkRtvrVtMapper {
    /**
     * 查询违章记录前2条
     * @return
     */
    List<DkRtvrDTO> getTop2Violation();
}
