package com.zhuanjingkj.stpbe.tebs.service.Impl;

import com.zhuanjingkj.stpbe.tebs.mapper.InsertMapper;
import com.zhuanjingkj.stpbe.tebs.rto.ImageRTO;
import com.zhuanjingkj.stpbe.tebs.rto.VehicleInformationRTO;
import com.zhuanjingkj.stpbe.tebs.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/

@Service
public class InsertServiceImpl implements InsertService {

    @Autowired
    InsertMapper insertMapper;

    @Override
    public void insertImage(ImageRTO image) {
        insertMapper.insertImage(image);
    }

    @Override
    public void insertVehicleInfo(VehicleInformationRTO vehicleInformationRTO) {
        insertMapper.insertVehicleInfo(vehicleInformationRTO);
    }
}
