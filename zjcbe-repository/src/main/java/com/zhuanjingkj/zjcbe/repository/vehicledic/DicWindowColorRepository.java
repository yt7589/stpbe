package com.zhuanjingkj.zjcbe.repository.vehicledic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.DicWindowColorEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.vehicledic.DicWindowColorMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DicWindowColorRepository extends ServiceImpl<DicWindowColorMapper, DicWindowColorEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public DicWindowColorEntity getByCode(String code) {
        EntityWrapper<DicWindowColorEntity> ew = new EntityWrapper<>();
        ew.eq("code", code);
        return this.selectOne(ew);
    }
}
