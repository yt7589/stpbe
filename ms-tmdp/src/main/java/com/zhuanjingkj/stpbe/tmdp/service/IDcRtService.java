package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcRtAreaJamDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcRtAreaVehicleDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcRtTimeJamDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcRtTimeVehicleDTO;

import java.util.List;

public interface IDcRtService {

    List<DcRtTimeJamDTO> getRtj_exp(String tp);

    List<DcRtAreaJamDTO> getRaj_exp(String tp);

    List<DcRtTimeVehicleDTO> getRtv_exp(String tp);

    List<DcRtAreaVehicleDTO> getRav_exp(String tp);
}
