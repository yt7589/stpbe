package com.zhuanjingkj.stpbe.tebs.service.Impl;

import com.zhuanjingkj.stpbe.tebs.mapper.CreateMapper;
import com.zhuanjingkj.stpbe.tebs.service.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/
@Service
public class CreateServiceImpl implements CreateService {

    private final static String IMAGE_TABLE_NAME = "t_image";
    private final static String VEHICLE_INFO_TABLE_NAME = "t_vehicle_information";

    @Autowired
    CreateMapper createMapper;
    @Override
    public String createNewImageTable() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
        String datStr = simpleDateFormat.format(date);
        String tableName = IMAGE_TABLE_NAME + "_"+datStr;
        createMapper.createNewImageTable(tableName);
        return tableName;
    }

    @Override
    public String createNewVehicleIfon() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
        String datStr = simpleDateFormat.format(date);
        String tableName = VEHICLE_INFO_TABLE_NAME + "_"+datStr;
        createMapper.createNewVehicleIfon(tableName);
        return tableName;
    }
}
