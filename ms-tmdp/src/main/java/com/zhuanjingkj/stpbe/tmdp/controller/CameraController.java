package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.res.CameraListDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.res.SiteListDTO;
import com.zhuanjingkj.stpbe.tmdp.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author by guoqiang
 * date on
 **/

@RestController
@RequestMapping("/camera")
@Validated
@CrossOrigin(origins = "*")
public class CameraController {

    @Autowired
    CameraService cameraService;

    @GetMapping("/site")
    public ResultDTO<SiteListDTO> getSite(){
        SiteListDTO siteListDTO = new SiteListDTO();
        List<SiteInfoDTO> siteInfoList = cameraService.getSite();
        CameraInfoDTO cameraInfoDTO = cameraService.getCameraInfo();
        siteListDTO.setSiteInfoList(siteInfoList);
        siteListDTO.setCameraInfo(cameraInfoDTO);
        return ResultDTO.success(siteListDTO);
    }

    @GetMapping("/site/device")
    public ResultDTO<CameraListDTO> getSiteCamera(@NotNull(message = "点位ID不能为空") Long siteId){
        CameraListDTO cameraListDTO = new CameraListDTO();

        List<CameraDTO> cameraList = cameraService.getCameraInfoBySite(siteId.toString());
        Integer cameraNum = cameraService.getAllCamera(siteId.toString());
        Integer snapMachineNum = cameraService.getAllSnapMachine(siteId.toString());

        cameraListDTO.setCameraList(cameraList);
        cameraListDTO.setCameraNum(cameraNum);
        cameraListDTO.setSnapMachineNum(snapMachineNum);
        return ResultDTO.success(cameraListDTO);
    }
}
