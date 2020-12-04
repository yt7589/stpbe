package com.zhuanjingkj.stpbe.tebs.service.Impl;

import com.zhuanjingkj.stpbe.data.entity.VehicleDistribution;
import com.zhuanjingkj.stpbe.tebs.dto.CameraDTO;
import com.zhuanjingkj.stpbe.tebs.mapper.SelectMapper;
import com.zhuanjingkj.stpbe.tebs.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/
@Service
public class SelectServiceImpl implements SelectService {

    @Autowired
    SelectMapper selectMapper;
    @Override
    public CameraDTO getCamera(String cameraId) {
        return selectMapper.getCamera(cameraId);
    }

    @Override
    public Long getImage(String tableName, String uid) {
        return selectMapper.getImage(tableName,uid);
    }

    @Override
    public List<VehicleDistribution> getVehicleDistribution() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);
        List<VehicleDistribution> list = selectMapper.getVehicleDistribution(dateStr);
        return list;
    }
}
