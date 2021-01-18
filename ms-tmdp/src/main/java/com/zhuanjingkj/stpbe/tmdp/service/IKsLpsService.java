package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsLpsAreaDTO;
import com.zhuanjingkj.stpbe.data.dto.KsLpsSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.KsLpsLalpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsLpsTimeDTO;

import java.util.List;

public interface IKsLpsService {

    List<KsLpsTimeDTO> getTimeAbnormalLicensePlate();

    List<KsLpsAreaDTO> getAreaAbnormalLicensePlate();

    List<KsLpsSiteDTO> getSiteAbnormalLicensePlate();

    List<KsLpsLalpDTO> getKsLpsLalp();
}
