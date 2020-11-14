package com.zhuanjingkj.zjcbe.repository.analysis;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleRtmpShotEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.analysis.VehicleRtmpShotMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleRtmpShotRepository extends ServiceImpl<VehicleRtmpShotMapper, VehicleRtmpShotEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean add(VehicleRtmpShotEntity entity) {
        return this.insert(entity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<VehicleRtmpShotEntity> getListByStreamId(Integer streamId, long timeStamp) {
        EntityWrapper<VehicleRtmpShotEntity> ew = new EntityWrapper<>();
        ew.eq("streamId", streamId);
        ew.eq("timeStamp", timeStamp);
        return this.selectList(ew);

    }
}
