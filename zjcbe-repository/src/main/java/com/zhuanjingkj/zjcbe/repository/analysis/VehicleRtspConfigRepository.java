package com.zhuanjingkj.zjcbe.repository.analysis;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleRtspconfigEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.analysis.VehicleRtspConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleRtspConfigRepository extends ServiceImpl<VehicleRtspConfigMapper, VehicleRtspconfigEntity> {
    private static Logger logger = LoggerFactory.getLogger(VehicleRtspConfigRepository.class);

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean add(VehicleRtspconfigEntity entity) {
        return this.insert(entity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public VehicleRtspconfigEntity getByRtmpUrl(String rtmpUrl) {
        System.out.println("###############  VehicleRtspConfigRepository.getByRtmpUrl 1 rtmpUrl: " + rtmpUrl + "!");
        EntityWrapper<VehicleRtspconfigEntity> ew = new EntityWrapper<>();
        ew.eq("rtmpUrl", rtmpUrl);
        System.out.println("###############  VehicleRtspConfigRepository.getByRtmpUrl 2 ew=" + ew + "!");
        List<VehicleRtspconfigEntity> lst = this.selectList(ew);
        System.out.println("###############  VehicleRtspConfigRepository.getByRtmpUrl 3 lst=" + lst + "!");
        logger.info("###################### logger logger logger ###################");
        if (lst != null && lst.size() > 0) {
            System.out.println("############### VehicleRtspConfigRepository.getByRtmpUrl 4");
            return lst.get(0);
        }
        System.out.println("############### VehicleRtspConfigRepository.getByRtmpUrl 5");
        return null;
    }
}
