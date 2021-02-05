package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVaDeviceDTO;

public interface ITnVaService {

    ResultDTO<DbQrsDTO> queryDeviceDeploy_exp(Integer startIndex, Integer amount, Integer direction);

    ResultDTO<TnVaDeviceDTO> queryDevice_exp();
}
