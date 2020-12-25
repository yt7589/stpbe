package com.zhuanjingkj.stpbe.tebs.mapper;

import com.zhuanjingkj.stpbe.tebs.vo.TvisJsonVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
