package com.zhuanjingkj.zjcbe.repository.vehicledic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.DicVehicleBrandEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.vehicledic.DicVehicleBrandMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DicVehicleBrandRepository extends ServiceImpl<DicVehicleBrandMapper, DicVehicleBrandEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public DicVehicleBrandEntity getByCode(String code) {
        EntityWrapper<DicVehicleBrandEntity> ew = new EntityWrapper<>();
        ew.eq("code", code);
        return this.selectOne(ew);
    }
}
