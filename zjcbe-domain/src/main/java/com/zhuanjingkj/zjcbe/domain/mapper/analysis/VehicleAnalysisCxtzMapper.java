package com.zhuanjingkj.zjcbe.domain.mapper.analysis;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleAnalysisCxtzEntity;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisCxtzPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/9/26 9:28
 **/
@Repository
@Mapper
public interface VehicleAnalysisCxtzMapper extends BaseMapper<VehicleAnalysisCxtzEntity> {
    public VehicleAnalysisCxtzPO getVehicleAnalysisCxtz(String analysisId);
}
