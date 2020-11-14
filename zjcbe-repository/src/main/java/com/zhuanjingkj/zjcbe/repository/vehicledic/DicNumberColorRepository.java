package com.zhuanjingkj.zjcbe.repository.vehicledic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.DicNumberColorEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.vehicledic.DicNumberColorMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DicNumberColorRepository extends ServiceImpl<DicNumberColorMapper, DicNumberColorEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public DicNumberColorEntity getByCode(String code) {
        EntityWrapper<DicNumberColorEntity> ew = new EntityWrapper<>();
        ew.eq("code", code);
        return this.selectOne(ew);
    }
}
