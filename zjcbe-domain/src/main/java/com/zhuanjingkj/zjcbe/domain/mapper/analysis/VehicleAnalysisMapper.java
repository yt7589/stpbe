package com.zhuanjingkj.zjcbe.domain.mapper.analysis;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleAnalysisEntity;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/9/26 9:28
 **/
@Repository
@Mapper
public interface VehicleAnalysisMapper extends BaseMapper<VehicleAnalysisEntity> {
    @Select("SELECT \n" +
            "\ta.analysisId,\n" +
            "\ta.imageId,\n" +
            "\ta.gcxh,\n" +
            "\ta.sxh,\n" +
            "\tppcx,\n" +
            "\tcllxfl,\n" +
            "\tcllxzfl,\n" +
            "\tppxhms,\n" +
            "\tcsys,\n" +
            "\tclpp,\n" +
            "\tppxhkxd,\n" +
            "\tcxnk,\n" +
            "\tbj,\n" +
            "\tgj,\n" +
            "\ttc,\n" +
            "\txlj,\n" +
            "\tdcjqs,\n" +
            "\tcszt,\n" +
            "\tcsps,\n" +
            "\tcsgh,\n" +
            "\tcsch,\n" +
            "\tywlshp,\n" +
            "\thpzt,\n" +
            "\thpys,\n" +
            "\thpkxd,\n" +
            "\tmwhpkxd,\n" +
            "\thpgg,\n" +
            "\thpwz,\n" +
            "\thpzl,\n" +
            "\thphm,\n" +
            "\tzjsksj,\n" +
            "\tmtcbdtk,\n" +
            "\tzjsbjaqd,\n" +
            "\tzjsddh,\n" +
            "\tfjszyb,\n" +
            "\tfjsbjaqd,\n" +
            "\tzjscy,\n" +
            "\tzjszyb,\n" +
            "\tclwz,\n" +
            "\tpsfx\n" +
            "FROM vehicle_analysis a\n" +
            "join vehicle_analysis_cxtz c on c.analysisId=a.analysisId\n" +
            "join vehicle_analysis_hptz h on h.analysisId=a.analysisId\n" +
            "join vehicle_analysis_gxhtz g on g.analysisId=a.analysisId\n" +
            "join vehicle_analysis_wztz w on w.analysisId=a.analysisId\n" +
            "join vehicle_analysis_jsxwtz j  on j.analysisId=a.analysisId\n" +
            "where a.imageId='${imageId}'")
    List<VehicleAnalysisPO> getAnalysisListByImageId(@Param("imageId") String imageId);
}
