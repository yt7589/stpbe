package com.zhuanjingkj.zjcbe.repository.sdk;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.VideoStreamEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.sdk.VideoStreamMapper;
import org.springframework.stereotype.Repository;

@Repository
public class VideoStreamRepository extends ServiceImpl<
        VideoStreamMapper, VideoStreamEntity
        > {
    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public long insertVideoStream(VideoStreamEntity entity) {
        this.insert(entity);
        return entity.getVideoStreamId();
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean updateVideoStream(VideoStreamEntity entity) {
        return this.updateById(entity);
    }
}
