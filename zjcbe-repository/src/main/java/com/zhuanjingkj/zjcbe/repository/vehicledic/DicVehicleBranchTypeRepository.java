package com.zhuanjingkj.zjcbe.repository.vehicledic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.DicVehicleBranchTypeEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.vehicledic.DicVehicleBranchTypeMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DicVehicleBranchTypeRepository extends ServiceImpl<DicVehicleBranchTypeMapper, DicVehicleBranchTypeEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public DicVehicleBranchTypeEntity getByCode(String code) {
        EntityWrapper<DicVehicleBranchTypeEntity> ew = new EntityWrapper<>();
        ew.eq("code", code);
        return this.selectOne(ew);
    }
}
