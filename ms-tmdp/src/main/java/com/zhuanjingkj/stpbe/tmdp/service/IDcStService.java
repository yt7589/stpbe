package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcStSysDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcStTodayDTO;

public interface IDcStService {

    DcStSysDTO getSys_exp();

    DcStTodayDTO getStToday_exp();
}
