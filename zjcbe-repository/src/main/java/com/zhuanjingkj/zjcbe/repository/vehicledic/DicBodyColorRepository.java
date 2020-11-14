package com.zhuanjingkj.zjcbe.repository.vehicledic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.DicBodyColorEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.vehicledic.DicBodyColorMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DicBodyColorRepository extends ServiceImpl<DicBodyColorMapper, DicBodyColorEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public DicBodyColorEntity getByCode(String code) {
        EntityWrapper<DicBodyColorEntity> ew = new EntityWrapper<>();
        ew.eq("code", code);
        return this.selectOne(ew);
    }
}
