package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.ks.AddVehicleToVcRTO;
import com.zhuanjingkj.stpbe.data.rto.ks.DeleteVehicleFromVcRTO;

public interface IKsVcService {

    ResultDTO<DbQrsDTO> queryVehicle_exp(String hphm, Integer startIndex, Integer amount, Integer direction);

    ResultDTO<DbInsertResultDTO> addVehicle_exp(AddVehicleToVcRTO rto);

    ResultDTO<DbDeleteResultDTO> deleteVehicle_exp(DeleteVehicleFromVcRTO rto);

    ResultDTO<DbQrsDTO> queryVcIllLsvs_exp();

    ResultDTO<DbQrsDTO> queryVcDynLsvs_exp();

    ResultDTO<DbQrsDTO> queryVcSfvs_exp();
}
