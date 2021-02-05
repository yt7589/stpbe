package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.vo.TvisJsonVO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvisJsonMapper {
    /**
     * 获取当前的t_tvis_json_x表名
     * @return 返回表名
     */
    public String getLatesTvisJsonTblName();

    /**
     * 生成指定名称的数据库表
     * @param tblName 数据库表名
     * @return 是否创建成功
     */
    public int createTvisJsonTbl(@Param("tblName") String tblName);

    /**
     * 向数据库表t_tvis_json中插入记录，记录JSON识别结果相关信息
     * @param tvisJsonVO
     * @return
     */
    public int insertTvisJson(@Param("tvisJsonVO") TvisJsonVO tvisJsonVO);

    /**
     * 获取指定视频的最新帧
     * @param tblName
     * @param streamId
     * @return
     */
    public TvisJsonVO getLatestStreamFrame(@Param("tblName") String tblName, @Param("streamId") long streamId);

    /**
     * 获取指定抓拍机最新图片
     * @param tblName
     * @param cameraId
     * @return
     */
    public TvisJsonVO getLatestCameraFrame(@Param("tblName") String tblName, @Param("cameraId") long cameraId);

    /**
     * 获取指定抓拍机指定图片的前一张图片
     * @param tblName
     * @param cameraId
     * @return
     */
    public TvisJsonVO getPrevCameraFrame(@Param("tblName") String tblName, @Param("cameraId") long cameraId,
                                         @Param("baseTvisJsonId") long baseTvisJsonId);

    /**
     * 获取指定抓拍机指定图片的后一张图片
     * @param tblName
     * @param cameraId
     * @return
     */
    public TvisJsonVO getNextCameraFrame(@Param("tblName") String tblName, @Param("cameraId") long cameraId,
                                         @Param("baseTvisJsonId") long baseTvisJsonId);

    /**
     * 获取当前所有t_tvis_json_*的数据库表名，按时间先后排列，新创建的在前面
     * @return
     */
    public List<String> getTvisJsonTblNames();

    /**
     * 在指定数据表tblName中查找指定tvisJsonId的记录，如果没有则返回null
     * @param tblName
     * @param tvisJsonId
     * @return
     */
    public TvisJsonVO getFrameByTvisJsonId(@Param("tblName") String tblName, @Param("tvisJsonId") long tvisJsonId);
}
