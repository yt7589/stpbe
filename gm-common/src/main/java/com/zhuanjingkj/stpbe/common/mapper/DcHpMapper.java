package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.DcHpDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DcHpMapper {

    Integer insertItfVehicle(@Param("dcHpDTO")DcHpDTO dcHpDTO, @Param("tvisJsonTbl") String tvisJsonTbl, @Param("tvisJsonId") long tvisJsonId, @Param("cllxzfl") String cllxzfl);

    List<DcHpDTO> getVehicleData(@Param("startIndex") int startIndex, @Param("amount") int amount, @Param("startTime") String startTime,
                                 @Param("endTime") String endTime, @Param("category") String category, @Param("vType") String vType,
                                 @Param("ilType") String ilType, @Param("hphm") String hphm,@Param("vAddr") String vAddr);

    Integer getVehicleCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("category") String category,
                            @Param("vType") String vType, @Param("ilType") String ilType, @Param("hphm") String hphm,
                            @Param("vAddr") String vAddr);

    List<Map<String, Object>> getVmDitCount(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> getVmDrtCount(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
