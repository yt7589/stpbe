package com.zhuanjingkj.zjcbe.repository.analysis;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleAnalysisEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.analysis.VehicleAnalysisMapper;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleAnalysisRepository extends ServiceImpl<VehicleAnalysisMapper, VehicleAnalysisEntity> {

	@SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
	public boolean batchAdd(List<VehicleAnalysisEntity> entity) {
		return this.insertBatch(entity);
	}

	@SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
	public List<VehicleAnalysisPO> getAnalysisListByImageId(String imageId) {
		return baseMapper.getAnalysisListByImageId(imageId);
	}
}
