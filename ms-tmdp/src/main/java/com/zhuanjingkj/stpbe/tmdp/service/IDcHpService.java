package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.DcHpDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpIlTrendDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpRgTrendDTO;

import java.util.List;

public interface IDcHpService {

    ResultDTO<DbQrsDTO> queryAllData_exp(int startIndex, int amount, Integer direction, String startTime, String endTime, String category, String vType, String ilType, String hphm, String vAddr);

    List<DcHpIlTrendDTO> getDit_exp();

    List<DcHpRgTrendDTO> getDrt_exp();

    List<DcHpDTO> getVehicleData(Integer startIndex, Integer amount, Integer direction, String startTime, String endTime, String category, String vType, String ilType, String hphm, String vAddr);

    Integer getVehicleCount();
}
