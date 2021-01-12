package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.KsSvsSvtvDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KsSvsSvtvMapper {
    List<KsSvsSvtvDTO> getKsSvsSvtv(@Param("tblName") String tblName);
}
