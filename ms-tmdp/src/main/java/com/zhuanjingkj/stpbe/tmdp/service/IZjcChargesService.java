package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.AddChargesRTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.DeleteChargesRTO;

public interface IChargesService {

    ResultDTO<DbQrsDTO> getCharges(Integer startIndex, Integer amount, Integer direction, Integer charges_id);

    ResultDTO<DbInsertResultDTO> addCharges(AddChargesRTO rto);

    ResultDTO<DbDeleteResultDTO> uptCharges(AddChargesRTO rto);

    ResultDTO<DbDeleteResultDTO> deleteCharges(DeleteChargesRTO rto);
}
