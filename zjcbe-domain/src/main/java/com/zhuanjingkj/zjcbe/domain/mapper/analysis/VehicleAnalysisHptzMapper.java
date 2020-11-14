package com.zhuanjingkj.zjcbe.domain.mapper.analysis;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleAnalysisHptzEntity;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisHptzPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/9/26 9:28
 **/
@Repository
@Mapper
public interface VehicleAnalysisHptzMapper extends BaseMapper<VehicleAnalysisHptzEntity> {
    public VehicleAnalysisHptzPO getVehicleAnalysisHptz(@Param("analysisId") String analysisId);
}
