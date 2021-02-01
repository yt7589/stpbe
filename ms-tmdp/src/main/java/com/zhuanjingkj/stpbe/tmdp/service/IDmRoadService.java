package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddRoadSectionToRsRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.DeleteRoadSectionFromRsRTO;

public interface IDmRoadService {
    ResultDTO<DbQrsDTO> queryRoad(Integer startIndex, Integer amount, Integer direction, String roadName);

    ResultDTO<DbInsertResultDTO> addRoadSection_exp(AddRoadSectionToRsRTO rto);

    ResultDTO<DbDeleteResultDTO> deleteRoadSection_exp(DeleteRoadSectionFromRsRTO rto);
}
