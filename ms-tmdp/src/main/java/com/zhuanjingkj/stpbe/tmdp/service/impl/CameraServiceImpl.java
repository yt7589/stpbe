package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.ImageDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.mapper.CameraMapper;
import com.zhuanjingkj.stpbe.tmdp.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    CameraMapper cameraMapper;
    @Override
    public List<SiteInfoDTO> getSite() {
        return cameraMapper.getSite();
    }

    @Override
    public CameraInfoDTO getCameraInfo() {
        CameraInfoDTO cameraInfoDTO = new CameraInfoDTO();
        Integer allCamera = Optional.ofNullable(cameraMapper.getAllCamera(null)).orElse(0);
        Integer allSnapMachine = Optional.ofNullable(cameraMapper.getAllSnapMachine(null)).orElse(0);
        Integer brokenCamera = Optional.ofNullable(cameraMapper.getBrokenCamera()).orElse(0);
        Integer brokenSnapMachine = Optional.ofNullable(cameraMapper.getBrokenSnapMachine()).orElse(0);
        cameraInfoDTO.setAllCamera(allCamera);
        cameraInfoDTO.setAllSnapMachine(allSnapMachine);
        cameraInfoDTO.setBrokenCamera(brokenCamera);
        cameraInfoDTO.setBrokenSnapMachine(brokenSnapMachine);
        return cameraInfoDTO;
    }

    @Override
    public List<CameraDTO> getCameraInfoBySite(String siteId) {
        return cameraMapper.getCameraInfoBySite(siteId);
    }

    @Override
    public Integer getAllCamera(String siteId) {
        return Optional.ofNullable(cameraMapper.getAllCamera(siteId)).orElse(0);
    }

    @Override
    public Integer getAllSnapMachine(String siteId) {
        return Optional.ofNullable(cameraMapper.getAllSnapMachine(siteId)).orElse(0);
    }

    @Override
    public ImageDTO getImgByCameraId(String cameraId) {

        String  tName = "t_image";
        ImageDTO imageDTO = cameraMapper.getImgByCameraId(tName,cameraId);
        return imageDTO;
    }
}
