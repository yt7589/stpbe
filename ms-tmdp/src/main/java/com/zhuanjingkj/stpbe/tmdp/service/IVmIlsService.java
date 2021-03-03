package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.tmdp.dto.vm.VmIlsVdDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vm.VmIlsVsInfoDTO;

import java.util.List;

public interface IVmIlsService {

    ResultDTO<DbQrsDTO> queryIllegalVehicle_epx(Integer startIndex, Integer amount, Integer direction, String startTime, String endTime,
                                                Integer category, String vType, String illType, String hphm, String addr);

    ResultDTO<List<VmIlsVehicleTypesDTO>> queryVehicleType_exp();

    ResultDTO<List<VmIlsTypeDTO>> queryIlsTypes_exp();

    ResultDTO<VmIlsVdDTO> queryIlsDat_exp(long tvisJsonId, Integer vehsIdx);

    ResultDTO<DbQrsDTO> queryIllegalVehicle_epx(String hphm, Integer startIndex, Integer amount, Integer direction);

    ResultDTO<VmIlsVsInfoDTO> queryIlsVsInfo_exp(String hphm, long tvisJsonId, Integer vehsIdx);

    List<VmIlsTopAreaDTO> queryIllArea(String startTime, String endTime, Integer category);

    List<VmIlsTopSiteDTO> queryIllSite(String startTime, String endTime, Integer category);

    ResultDTO<DbQrsDTO> queryIllegal_exp(Integer startIndex, Integer amount, Integer direction, String startTime, String endTime);

    ResultDTO<DbQrsDTO> querySiteIllegal_exp(String startTime, String endTime, Integer category);

    Integer getIlsCount(String startTime, String endTime, Integer category, String vType, String illType, String hphm, String addr);

    List<VmIlsDTO> getIlsPart(long startIndex, long amount, Integer direction, String startTime, String endTime,
                              Integer category, String vType, String illType, String hphm, String addr);

    Integer getVIlsHistory(String hphm);

    List<VmIlsVhsDTO> queryIlsVehicleHistoric(String hphm, Integer startIndex, Integer amount);
}
