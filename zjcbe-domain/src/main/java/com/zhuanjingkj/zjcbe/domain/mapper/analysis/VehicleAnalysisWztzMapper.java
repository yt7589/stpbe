package com.zhuanjingkj.zjcbe.domain.mapper.analysis;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleAnalysisWztzEntity;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisWztzPO;
import org.apache.ibatis.annotations.*;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/9/26 9:28
 **/
@Mapper
public interface VehicleAnalysisWztzMapper extends BaseMapper<VehicleAnalysisWztzEntity> {
    /*@Select("select count(id) as vehicle_num from vehicle_analysis_wztz where analysisId=(select max(analysisId) from vehicle_analysis where imageId=#{imageId})")
    @Results(id="imageId", value={
            @Result(property="vehicleNum", column="vehicle_num")
    })*/
    VehicleAnalysisWztzPO getVehicleNumInImage(@Param("imageId") String imageId);

    /**
     * 获取车辆识别位置特征信息
     * @param analysisId
     * @return
     */
    VehicleAnalysisWztzPO getVehicleAnalysisWztz(@Param("analysisId") String analysisId);
}
