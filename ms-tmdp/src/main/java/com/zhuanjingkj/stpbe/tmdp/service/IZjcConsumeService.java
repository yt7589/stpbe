package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import org.springframework.stereotype.Service;

public interface IZjcConsumeService {

    ResultDTO<DbQrsDTO> getConsumeRecord(Integer startIndex, Integer amount, Integer direction, Integer customerId);

}
