package com.zhuanjingkj.zjcbe.repository.dc;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.NoneEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.dc.DcVideoRecgMapper;
import com.zhuanjingkj.zjcbe.commondata.po.VideoStreamPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DcVideoRecgRepository extends ServiceImpl<DcVideoRecgMapper, NoneEntity> {
    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<VideoStreamPO> getUserVideoStreamIds(int accountId) {
        return baseMapper.getVideoStreamIds(accountId);
    }
}
