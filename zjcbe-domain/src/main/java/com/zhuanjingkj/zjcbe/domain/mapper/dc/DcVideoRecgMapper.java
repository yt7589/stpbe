package com.zhuanjingkj.zjcbe.domain.mapper.dc;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.NoneEntity;
import com.zhuanjingkj.zjcbe.commondata.po.VideoStreamPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DcVideoRecgMapper extends BaseMapper<NoneEntity> {
    public List<VideoStreamPO> getVideoStreamIds(@Param("accountId") int accountId);
}
