package com.zhuanjingkj.zjcbe.repository.vehicledic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.DicVehicleTypeEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.vehicledic.DicVehicleTypeMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DicVehicleTypeRepository extends ServiceImpl<DicVehicleTypeMapper, DicVehicleTypeEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public DicVehicleTypeEntity getByCode(String code) {
        EntityWrapper<DicVehicleTypeEntity> ew = new EntityWrapper<>();
        ew.eq("code", code);
        return this.selectOne(ew);
    }
}
