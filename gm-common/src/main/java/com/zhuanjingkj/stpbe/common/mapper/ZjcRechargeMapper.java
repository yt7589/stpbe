package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.ZjcRechargeRecordDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.ZjcRechargeRTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZjcRechargeMapper {

    List<ZjcRechargeRecordDTO> getRechargeRecord(@Param("customer") String customerId, @Param("startIndex") Integer startIndex,
                                                 @Param("amount") Integer amount, @Param("orderno") String orderno);

    Integer getRechargeCount(@Param("customer") String customerId, @Param("orderno") String orderno);

    Integer recharge(@Param("rto") ZjcRechargeRTO rto);

    ResultDTO<DbQrsDTO> getCustomers();
}
