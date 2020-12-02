package com.zhuanjingkj.stpbe.tebs.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/
@Repository
public interface CreateMapper {

    /**
     * 创建图片表
     * @param tableName
     */
    void createNewImageTable(@Param("tableName") String tableName);

}
