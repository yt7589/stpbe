package com.zhuanjingkj.stpbe.tebs.service.Impl;

import com.zhuanjingkj.stpbe.tebs.dto.CameraDTO;
import com.zhuanjingkj.stpbe.tebs.mapper.SelectMapper;
import com.zhuanjingkj.stpbe.tebs.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
