package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcLrDTO;

public interface IDcLrService {
    ResultDTO<DcLrDTO> queryLocusReplay_exp(String startTime, String endTime, String hphm);
}
