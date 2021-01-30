package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DcLrSiteDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DcLrMapper {

    List<DcLrSiteDTO> getRouteInfo(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("hphm") String hphm);

}
