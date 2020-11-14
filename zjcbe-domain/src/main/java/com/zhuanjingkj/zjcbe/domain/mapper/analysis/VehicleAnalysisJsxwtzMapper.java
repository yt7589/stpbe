package com.zhuanjingkj.zjcbe.domain.mapper.analysis;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleAnalysisJsxwtzEntity;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisJsxwtzPO;
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
public interface VehicleAnalysisJsxwtzMapper extends BaseMapper<VehicleAnalysisJsxwtzEntity> {
    public VehicleAnalysisJsxwtzPO getVehicleAnalysisJsxwtz(@Param("analysisId") String analysisId);
}
