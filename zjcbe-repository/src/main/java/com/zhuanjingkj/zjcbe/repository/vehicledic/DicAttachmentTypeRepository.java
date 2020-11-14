package com.zhuanjingkj.zjcbe.repository.vehicledic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.DicAttachmentTypeEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.vehicledic.DicAttachmentTypeMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DicAttachmentTypeRepository extends ServiceImpl<DicAttachmentTypeMapper, DicAttachmentTypeEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public DicAttachmentTypeEntity getByCode(String code) {
        EntityWrapper<DicAttachmentTypeEntity> ew = new EntityWrapper<>();
        ew.eq("code", code);
        return this.selectOne(ew);
    }
}
