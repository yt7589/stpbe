package com.zhuanjingkj.zjcbe.repository.analysis;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleAnalysisWztzEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.analysis.VehicleAnalysisWztzMapper;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisWztzPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleAnalysisWztzRepository extends ServiceImpl<VehicleAnalysisWztzMapper, VehicleAnalysisWztzEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean batchAdd(List<VehicleAnalysisWztzEntity> entity) {
        return this.insertBatch(entity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public VehicleAnalysisWztzPO getVehicleNumInImage(String imageId) {
        return baseMapper.getVehicleNumInImage(imageId);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public VehicleAnalysisWztzPO getVehicleAnalysisWztz(String analysisId) {
        return baseMapper.getVehicleAnalysisWztz(analysisId);
    }
}
