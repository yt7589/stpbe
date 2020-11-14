package com.zhuanjingkj.zjcbe.repository.sdk;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.NoneEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.sdk.SdkQueryMapper;
import com.zhuanjingkj.zjcbe.commondata.po.VideoStreamPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SdkQueryDAO extends ServiceImpl<SdkQueryMapper, NoneEntity> {
    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<VideoStreamPO> getVideoStreamsDemo() {
        return baseMapper.getVideoStreamsDemo();
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public int getVideoStreamsTotal(int state) {
        return baseMapper.getVideoStreamsTotal(state);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<VideoStreamPO> getVideoStreams(int startIdx, int amount, int direction, int state) {
        return baseMapper.getVideoStreams(startIdx - 1, amount, state);
    }
}
