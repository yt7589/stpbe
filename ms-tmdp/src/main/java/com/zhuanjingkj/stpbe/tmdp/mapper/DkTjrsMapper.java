package com.zhuanjingkj.stpbe.tmdp.mapper;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DkTjrsMapper {
    /**
     * 查询首页路段车流量
     * @return
     */
    public List<Map> getRoad();

}
