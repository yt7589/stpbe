package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.KsVcDTO;
import com.zhuanjingkj.stpbe.data.rto.ks.AddVehicleToVcRTO;
import com.zhuanjingkj.stpbe.data.rto.ks.DeleteVehicleFromVcRTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KsVcMapper {

    Integer getKsvcCount(@Param("hphm") String hphm);

    List<KsVcDTO> getKsvc(@Param("hphm") String hphm, @Param("startIndex") Integer startIndex, @Param("amount") Integer amount);

    Integer addVehicle(@Param("rto") AddVehicleToVcRTO rto);

    Integer deleteVehicle(@Param("rto") DeleteVehicleFromVcRTO rto);

    List<String> getKsvcHphm();
}
