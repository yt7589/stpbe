package com.zhuanjingkj.zjcbe.domain.mapper.analysis;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleImagesEntity;
import com.zhuanjingkj.zjcbe.domain.po.VehicleImagesPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/9/26 9:28
 **/
@Repository
@Mapper
public interface VehicleImagesMapper extends BaseMapper<VehicleImagesEntity> {
    @Select("select i.*,s.sxh,s.imagePath as thumbImage\n" +
            "from vehicle_rtmp_shot s\n" +
            "join vehicle_images i on s.streamId=i.streamId and s.timeStamp=i.timeStamp\n" +
            "where s.streamId=#{streamId}\n" +
            "order by s.id desc limit 0,1")
    VehicleImagesPO getLastVehicleImageByStreamId(@Param("streamId") String streamId);

    /**
     * 求出指定用户图片识别列表
     */
    @Select("select count(imageId) from vehicle_images where uploadBy=#{accountId} and uploadTime>=#{startTime} and uploadTime<#{endTime}")
    int getImageRecgTotalNum(@Param("accountId") int accountId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("select imageId, uploadTime, imageUrl from vehicle_images where uploadBy=#{accountId} and uploadTime>=#{startTime} and uploadTime<#{endTime} order by uploadTime desc limit #{startIdx}, #{amount}")
    @Results(id="imageId", value={
            @Result(property="imageId", column="imageId"),
            @Result(property="uploadTime", column="uploadTime"),
            @Result(property="imageUrl", column ="imageUrl")
    }
    )
    public List<VehicleImagesPO> getImageRecgDatas(long accountId, String startTime,
                                                   String endTime, int startIdx, int amount, int direction);

    public VehicleImagesPO getImageInfo(@Param("imageId") String imageId);
}
