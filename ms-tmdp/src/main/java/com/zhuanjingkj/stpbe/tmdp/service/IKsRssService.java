package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.AddRsToRssRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.DeleteRsFromRssRTO;

public interface IKsRssService {

    ResultDTO<DbQrsDTO> queryRsSupervision_exp(String rssName, Integer startIndex, Integer amount, Integer direction, Integer type);

    ResultDTO<DbDeleteResultDTO> deleteKsRsSupervision_exp(DeleteRsFromRssRTO rto);

    ResultDTO<DbInsertResultDTO> addRsToRsSupervision_exp(AddRsToRssRTO rto);
}
