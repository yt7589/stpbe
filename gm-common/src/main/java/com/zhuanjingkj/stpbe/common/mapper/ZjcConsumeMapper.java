package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.ZjcConsumeRecordDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZjcConsumeMapper {

    List<ZjcConsumeRecordDTO> getConsumeRecord(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount,
                                               @Param("customerId") Integer customerId);

    Integer getConsumeCount(@Param("customerId") Integer customerId);
}
