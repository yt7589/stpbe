package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.*;

import java.util.List;

public interface IDcRtService {

    List<DcRtTimeJamDTO> getRtj_exp(String tp);

    List<DcRtAreaJamDTO> getRaj_exp(String tp);

    List<DcRtTimeVehicleDTO> getRtv_exp(String tp);

    List<DcRtAreaVehicleDTO> getRav_exp(String tp);

    List<DcRtRoadJamDTO> getRrj_exp(String tp);

    ResultDTO<DcRtDTO> queryDataReport_exp(String tp);
}
