package com.zhuanjingkj.zjcbe.repository.analysis;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleVideosEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.analysis.VehicleVideosMapper;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleVideosRepository extends ServiceImpl<VehicleVideosMapper, VehicleVideosEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean add(VehicleVideosEntity entity) {
        return this.insert(entity);
    }
}
