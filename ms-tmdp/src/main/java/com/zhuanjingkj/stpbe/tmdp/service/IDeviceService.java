package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddDeviceToDsRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteDeviceFromDsRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateDeviceInfoRTO;

/**
 * 设备
 */
public interface IDeviceService {

    ResultDTO<DbQrsDTO> queryDevice_exp(Integer startIndex, Integer amount, Integer direction, String type, String code);

    ResultDTO<DbDeleteResultDTO> deleteDevice_exp(DeleteDeviceFromDsRTO rto);

    ResultDTO<DbInsertResultDTO> addDevice_exp(AddDeviceToDsRTO rto);

    ResultDTO<DbQrsDTO> queryDeviceType_exp();

    ResultDTO<DbQrsDTO> queryDeviceNode_exp();

    ResultDTO<DbDeleteResultDTO> updateDeviceInfo_exp(UpdateDeviceInfoRTO rto);
}
