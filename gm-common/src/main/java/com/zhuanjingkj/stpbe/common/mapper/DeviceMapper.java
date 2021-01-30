package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DmDeviceDTO;
import com.zhuanjingkj.stpbe.data.dto.DmDeviceNodeDTO;
import com.zhuanjingkj.stpbe.data.dto.DmDeviceTypeDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddDeviceToDsRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateDeviceInfoRTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceMapper {

    List<DmDeviceDTO> getDmDevice(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount, @Param("type") String type, @Param("code") String code);

    Integer getDmDeviceCount(@Param("type") String type, @Param("code") String code);

    Integer deleteDevice(@Param("deviceNo") String deviceNo);

    Integer addDevice(@Param("rto") AddDeviceToDsRTO rto);

    List<DmDeviceTypeDTO> getDmDeviceType();

    List<DmDeviceNodeDTO> queryDeviceNode();

    Integer updateDeviceInfo(@Param("rto") UpdateDeviceInfoRTO rto);
}
