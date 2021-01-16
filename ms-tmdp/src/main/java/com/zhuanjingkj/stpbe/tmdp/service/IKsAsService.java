package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.AddAreasToKeyAreasRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.DeleteAreaFromKeyAreasRTO;

public interface IKsAsService {

    ResultDTO<DbQrsDTO> queryKeyAreas_exp(String areaName, Integer startIndex, Integer amount, Integer direction, Integer type);

    ResultDTO<DbDeleteResultDTO> deleteAreaFromKeyAreas_exp(DeleteAreaFromKeyAreasRTO rto);

    ResultDTO<DbInsertResultDTO> addAreasToKeyAreas_exp(AddAreasToKeyAreasRTO rto);
}
