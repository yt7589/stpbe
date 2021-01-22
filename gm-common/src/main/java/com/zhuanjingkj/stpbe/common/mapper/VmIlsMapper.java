package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VmIlsMapper {

    List<VmIlsDTO> getIllegalVehicle(@Param("tblName") String tblName, @Param("startIndex") Integer startIndex, @Param("amount") Integer amount, @Param("startTime") String startTime,
                                     @Param("endTime") String endTime, @Param("category") Integer category, @Param("cg") String cg, @Param("vType") String vType, @Param("illType") String illType,
                                     @Param("hphm") String hphm, @Param("addr") String addr);

    Integer getIllegalVehicleCount(@Param("tblName") String tblName, @Param("startIndex") Integer startIndex, @Param("amount") Integer amount, @Param("startTime") String startTime,
                                   @Param("endTime") String endTime, @Param("category") Integer category, @Param("cg") String cg, @Param("vType") String vType, @Param("illType") String illType,
                                   @Param("hphm") String hphm, @Param("addr") String addr);

    List<VmIlsVehicleTypesDTO> getVType(@Param("level") Integer level);

    List<VmIlsTypeDTO> getIlsType();

    Map<String, Object> getFileHash(@Param("tvId") long tvId);

    List<Map<String, Object>> getVColor();

    List<Map<String, Object>> getHColor();

    List<Map<String, Object>> getHpzl();

    List<VmIlsVhsDTO> getVIlRecord(@Param("hphm") String hphm, @Param("startIndex") Integer startIndex, @Param("amount") Integer amount);

    Integer getVIlCount(@Param("hphm") String hphm);

    List<Map<String, Object>> getIlTypeByHphm(@Param("hphm") String hphm);

    List<Map<String, Object>> getIlTypeByYear(@Param("hphm") String hphm);

    List<VmIlsTopAreaDTO> getIllTopArea(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("category") Integer category,
                                     @Param("cg") String cg);

    List<VmIlsTopSiteDTO> getIlsTopSite(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("category") Integer category,
                                     @Param("cg") String cg);

    List<VmIlssDTO> getIlsSite(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount, @Param("startTime") String startTime, @Param("endTime") String endTime);

    Integer getIlsSiteCount();

    List<VmIlsSiteDTO> getIlsMapSite(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("category") Integer category, @Param("cg") String cg);

    Integer getIlsCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("category") Integer category,
                        @Param("cg") String cg, @Param("vType") String vType, @Param("illType") String illType, @Param("hphm") String hphm, @Param("addr") String addr);

    List<VmIlsDTO> getIlsPart(@Param("tblName") String tblName, @Param("startIndex") long startIndex, @Param("amount") long amount, @Param("startTime") String startTime,
                              @Param("endTime") String endTime, @Param("category") Integer category, @Param("cg") String cg, @Param("vType") String vType, @Param("illType") String illType,
                              @Param("hphm") String hphm, @Param("addr") String addr);

    Integer getVIlsHistoryCount(@Param("hphm") String hphm);

}
