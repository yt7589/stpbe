package com.zhuanjingkj.stpbe.tebs.mapper;

import com.zhuanjingkj.stpbe.tebs.rto.ImageRTO;
import org.springframework.stereotype.Repository;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/

@Repository
public interface InsertMapper {

    /**
     * 插入图片
     */
    void insertImage(ImageRTO image);
}
