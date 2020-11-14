package com.zhuanjingkj.zjcbe.repository.analysis;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.ImageRectifyEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.analysis.ImageRectifyMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRectifyRepository extends ServiceImpl<ImageRectifyMapper, ImageRectifyEntity> {
    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean add(ImageRectifyEntity entity) {
        return this.insert(entity);
    }
}
