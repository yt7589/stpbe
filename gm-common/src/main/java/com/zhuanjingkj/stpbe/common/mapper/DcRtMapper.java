package com.zhuanjingkj.stpbe.common.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DcRtMapper {

    List<Map<String, Object>> getDcForWeek(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> getDcFor3m(@Param("startTime") String startTime,  @Param("endTime") String pEndTime);

    List<Map<String, Object>> getDcForDay(@Param("endTime") String endTime, @Param("pEndTime") String pEndTime);

    List<Map<String, Object>> getRajForDay(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("pStartTime") String pStartTime);

    List<Map<String, Object>> getRavForDay(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("pStartTime") String pStartTime);

    List<Map<String, Object>> getRrjForDay(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("pStartTime") String pStartTime);

    Integer getTivTotal(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> getRtvForDay(@Param("startTime") String startTime, @Param("endTime") String endTime);

    Map<String, Object> getDcRt(@Param("tp") String tp, @Param("date") String date);
}
