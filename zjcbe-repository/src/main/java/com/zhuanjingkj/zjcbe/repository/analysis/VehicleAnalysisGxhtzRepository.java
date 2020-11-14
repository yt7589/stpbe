package com.zhuanjingkj.zjcbe.repository.analysis;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleAnalysisGxhtzEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.analysis.VehicleAnalysisGxhtzMapper;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisGxhtzPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleAnalysisGxhtzRepository extends ServiceImpl<VehicleAnalysisGxhtzMapper, VehicleAnalysisGxhtzEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean batchAdd(List<VehicleAnalysisGxhtzEntity> entity) {
        return this.insertBatch(entity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public VehicleAnalysisGxhtzPO getVehicleAnalysisGxhtz(String analysisId) {
        return baseMapper.getVehicleAnalysisGxhtz(analysisId);
    }
}
