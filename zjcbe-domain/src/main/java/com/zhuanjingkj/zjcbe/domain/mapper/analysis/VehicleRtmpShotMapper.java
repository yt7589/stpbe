package com.zhuanjingkj.zjcbe.domain.mapper.analysis;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleRtmpShotEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description: rtmp视频截图处理
 * @author: liuxiaogang.bj
 * @create: 2019/9/26 9:28
 **/
@Repository
@Mapper
public interface VehicleRtmpShotMapper extends BaseMapper<VehicleRtmpShotEntity> {
}
