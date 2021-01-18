package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.KsSvsLtviDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KsSvsLtviMapper {
    KsSvsLtviDTO getKsSvsLtvi(@Param("tblName") String tblName);
}
