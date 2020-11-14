package com.zhuanjingkj.zjcbe.repository.vehicledic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.DicNumberTypeEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.vehicledic.DicNumberTypeMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DicNumberTypeRepository extends ServiceImpl<DicNumberTypeMapper, DicNumberTypeEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public DicNumberTypeEntity getByCode(String code) {
        EntityWrapper<DicNumberTypeEntity> ew = new EntityWrapper<>();
        ew.eq("code", code);
        return this.selectOne(ew);
    }
}
