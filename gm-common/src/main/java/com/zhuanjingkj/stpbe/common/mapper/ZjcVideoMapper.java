package com.zhuanjingkj.stpbe.common.mapper;

import org.apache.ibatis.annotations.Param;

public interface ZjcVideoMapper {

    Integer addRtmp(@Param("consumerId") Integer consumerId, @Param("fileName") String fileName, @Param("streamId") String streamId, @Param("date") String date);

    String getVideoStreamId(@Param("fileName") String fileName);
}
