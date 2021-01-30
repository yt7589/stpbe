package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfDTO;

public interface ITptfService {
    ResultDTO<TpTfDTO> queryTrafficForecast_exp(String date, String time);
}
