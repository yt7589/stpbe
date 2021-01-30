package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpDTO;

public interface ITpService {
    ResultDTO<TpDTO> queryTrafficPrognosis_exp(String startTime, String endTime, String hphm);
}
