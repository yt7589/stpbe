package com.zhuanjingkj.stpbe.tmdp.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DkDctfMapper {
    /**
     * 查询首页地区车流量
     * @return
     */
    public List<Map> getArea();
}
