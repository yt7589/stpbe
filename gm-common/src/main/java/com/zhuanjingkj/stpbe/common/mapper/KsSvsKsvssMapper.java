package com.zhuanjingkj.stpbe.common.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public interface KsSvsKsvssMapper {
    List<Map<String, Object>> getKsvss(@Param("list") List<String> list);
}
