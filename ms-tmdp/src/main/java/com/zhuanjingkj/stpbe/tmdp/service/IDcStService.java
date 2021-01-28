package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DcStIlSiteDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.*;
import com.zhuanjingkj.stpbe.data.dto.DcStVAreaDTO;
import com.zhuanjingkj.stpbe.data.dto.DcStVSiteDTO;

import java.util.List;

public interface IDcStService {

    DcStSysDTO getSys_exp();

    DcStTodayDTO getStToday_exp();

    List<DcStVAreaDTO> getVarea_exp();

    List<DcStVSiteDTO> getVSite_exp();

    List<DcStVTrendDTO> getVTr_exp();

    DcStVDTO getVst_exp();

    List<DcStIlSiteDTO> getIlSite_exp();

    List<DcStKvSiteDTO> getDcKvs_exp();

    List<DcStTruckSiteDTO> getDcTruckSite_exp();
}
