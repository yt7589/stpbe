package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;

public interface IDcCsService {
    public ResultDTO<DbQrsDTO> queryVehicleByGraph(String cltzxl, String psfx, String cllxfl,
                                                   String cllxzfl, String startDate, String endDate,
                                                   String startTime, String endTime,
                                                   int startIndex, int amount);
}
