package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVaDeviceDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVaSiteInfoDTO;

public interface ITnVaService {

    ResultDTO<DbQrsDTO> queryDeviceDeploy_exp();

    ResultDTO<TnVaDeviceDTO> queryDevice_exp();

    ResultDTO<TnVaSiteInfoDTO> querySdInfo_exp(Integer startIndex, Integer amount, Integer direction, long siteId);
}
