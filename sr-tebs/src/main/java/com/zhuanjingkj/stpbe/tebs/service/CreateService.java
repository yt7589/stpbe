package com.zhuanjingkj.stpbe.tebs.service;

import org.apache.ibatis.annotations.Param;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/
public interface CreateService {

    /**
     * 创建图片表
     */
    String createNewImageTable();

    /**
     * 创建车辆基本信息表
     * @return
     */
    String createNewVehicleIfon();
}
