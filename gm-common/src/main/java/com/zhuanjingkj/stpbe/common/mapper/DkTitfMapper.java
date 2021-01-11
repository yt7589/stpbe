package com.zhuanjingkj.stpbe.common.mapper;

import org.apache.ibatis.annotations.Param;

public interface DkTitfMapper {
    /**
     * 查询某个时间段车流量
     * @param maxTime 时间段上线
     * @param minTime 时间段下线
     * @return
     */
    public int countByTime(@Param("maxTime")String maxTime, @Param("minTime")String minTime);
}
