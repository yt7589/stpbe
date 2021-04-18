package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.ZjcBillSummaryDTO;

public interface IZjcBillService {

    ResultDTO<DbQrsDTO> getBills(Integer startIndex, Integer amount, Integer direction, Integer customerId);

    ResultDTO<ZjcBillSummaryDTO> getBillSummary(Integer customerId);
}
