package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.KsRssDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KsRssMapper {

    Integer getKsRoadCount(@Param("rssName")String rssName, @Param("type") Integer type);

    List<KsRssDTO> getKsRoad(@Param("rssName")String rssName, @Param("startIndex")Integer startIndex, @Param("amount")Integer amount, @Param("type") Integer type);

    Integer deleteKsRoad(@Param("rssId")long rssId);

    Integer addRoads(@Param("rssIds")List<Integer> rssIds);
}
