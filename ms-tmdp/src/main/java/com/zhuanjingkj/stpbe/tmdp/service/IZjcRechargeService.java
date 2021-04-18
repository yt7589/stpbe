package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.ZjcRechargeRTO;

public interface IZjcRechargeService {

    ResultDTO<DbQrsDTO> getRechargeRecord(String customer, Integer startIndex, Integer amount,
                                          Integer direction, String orderno);

    ResultDTO<DbInsertResultDTO> recharge(ZjcRechargeRTO rto);

    ResultDTO<DbQrsDTO> getCustomers();
}
