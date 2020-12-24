package com.zhuanjingkj.stpbe.tebs.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface TvisJsonMapper {
    /**
     * 获取当前的t_tvis_json_x表名
     * @return 返回表名
     */
    public String getLatesTvisJsonTblName();
}
