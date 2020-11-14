package com.zhuanjingkj.zjcbe.repository.analysis;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleImagesEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.analysis.VehicleImagesMapper;
import com.zhuanjingkj.zjcbe.domain.po.VehicleImagesPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleImagesRepository extends ServiceImpl<VehicleImagesMapper, VehicleImagesEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean add(VehicleImagesEntity entity) {
        return this.insert(entity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public VehicleImagesPO getLastVehicleImage(String streamId) {
        return baseMapper.getLastVehicleImageByStreamId(streamId);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public int getImageRecgTotalNum(long accountId, String startTime, String endTime) {
        return baseMapper.getImageRecgTotalNum((int)accountId, startTime, endTime);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<VehicleImagesPO> getImageRecgDatas(long accountId, String startTime,
                                                   String endTime, int startIdx, int amount, int direction) {
        return baseMapper.getImageRecgDatas(accountId, startTime, endTime, startIdx, amount, direction);
    }

    /**
     * 获取指定图片的基本信息
     * @param imageId
     * @return
     */
    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public VehicleImagesPO getImageInfo(String imageId) {
        return baseMapper.getImageInfo(imageId);
    }
}
