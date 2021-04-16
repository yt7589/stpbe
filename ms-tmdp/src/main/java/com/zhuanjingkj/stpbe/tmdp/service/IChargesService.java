package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.AddCharges;

public interface IChargesService {

    ResultDTO<DbQrsDTO> getCharges(Integer startIndex, Integer amount, Integer direction, Integer charges_id);

    ResultDTO<DbInsertResultDTO> addCharges(AddCharges rto);

    ResultDTO<DbDeleteResultDTO> uptCharges(AddCharges rto);
}
