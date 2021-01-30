package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DcStIlSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.DcStVAreaDTO;
import com.zhuanjingkj.stpbe.data.dto.DcStVMDTO;
import com.zhuanjingkj.stpbe.data.dto.DcStVSiteDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DcStMapper {

    Integer getDeviceCount();

    Integer getVehicleControl();

    Integer getIlCount();

    Integer getDvCount();

    List<DcStVAreaDTO> getTop5Varea();

    List<DcStVSiteDTO> getTop5VSite();

    List<Map<String, Object>> getWeekVehicle(@Param("endTime") String endTime, @Param("startTime") String startTime);

    Integer getTodaySt(@Param("date")String date);

    Integer getWeekSt(@Param("startTime")String startTime, @Param("endTime")String endTime);

    Integer getMonthSt(@Param("month")String month);

    List<Map<String, Object>> getDcstVmd(@Param("year") Integer year);

    List<DcStIlSiteDTO> getDcstIlTop7Site();

    List<Map<String, Object>> getSiteInfo();
}
