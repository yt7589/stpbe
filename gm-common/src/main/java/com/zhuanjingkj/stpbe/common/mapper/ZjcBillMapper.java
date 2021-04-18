package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.ZjcBillDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZjcBillMapper {

    List<ZjcBillDTO> getBills(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount,
                              @Param("customerId") Integer customerId);

    Integer getBillCount(@Param("customerId") Integer customerId);

    Double getTotalRechargeByCustomerId(@Param("customerId") Integer customerId);

    Double getBalanceByCustomerId(@Param("customerId") Integer customerId);

    Integer getPicCountByCustomerId(@Param("customerId") Integer customerId);

    Integer getVideoDurationByCustomerId(@Param("customerId") Integer customerId);
}
