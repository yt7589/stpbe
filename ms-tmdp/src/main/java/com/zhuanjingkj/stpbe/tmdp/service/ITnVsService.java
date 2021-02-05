package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.TnVsVehicleDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsSiteDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVsTopSiteDTO;

import java.util.List;

public interface ITnVsService {

    List<TnVsTopSiteDTO> getTvtsDTO_exp();

    TnVsVehicleDTO getTvtvdDTO_exp();

//    List<TnVsSiteDTO> getTvsdDTO_exp();
}
