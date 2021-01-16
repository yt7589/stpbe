package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.KsLpsLalpDTO;
import com.zhuanjingkj.stpbe.data.dto.KsLpsSiteDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KsLpsMapper {

    List<KsLpsSiteDTO> getLpsSite();

    Integer getLpsSiteCount(@Param("siteId") long siteId, @Param("hphm") String hphm);

    List<KsLpsLalpDTO> getLpsLalp(@Param("tblName") String tblName);
}
