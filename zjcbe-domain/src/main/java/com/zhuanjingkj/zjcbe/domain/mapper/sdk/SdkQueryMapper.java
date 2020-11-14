package com.zhuanjingkj.zjcbe.domain.mapper.sdk;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.NoneEntity;
import com.zhuanjingkj.zjcbe.commondata.po.VideoStreamPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SdkQueryMapper extends BaseMapper<NoneEntity> {
    public List<VideoStreamPO> getVideoStreamsDemo();
    public int getVideoStreamsTotal(int state);
    public List<VideoStreamPO> getVideoStreams(int startIdx, int amount, int state);
}
