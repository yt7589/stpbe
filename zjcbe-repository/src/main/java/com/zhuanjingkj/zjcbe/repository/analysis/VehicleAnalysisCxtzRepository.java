package com.zhuanjingkj.zjcbe.repository.analysis;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleAnalysisCxtzEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.analysis.VehicleAnalysisCxtzMapper;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisCxtzPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleAnalysisCxtzRepository extends ServiceImpl<VehicleAnalysisCxtzMapper, VehicleAnalysisCxtzEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean batchAdd(List<VehicleAnalysisCxtzEntity> entity) {
        return this.insertBatch(entity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public VehicleAnalysisCxtzPO getVehicleAnalysisCxtz(String analysisId) {
        return baseMapper.getVehicleAnalysisCxtz(analysisId);
    }
}
