package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleImgUrl;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleInformationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleNumberDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleTimeFrameDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.res.EmphasisVehicleListDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.service.EmphasisVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.24
 **/

@RestController
@RequestMapping("/emphasis/vehicle")
@CrossOrigin(origins = "*")
public class EmphasisVehicleController {

    @Autowired
    private EmphasisVehicleService emphasisVehicleService;

    @GetMapping()
    public ResultDTO<EmphasisVehicleListDTO> getEmphasisVehicleInfo() {
        EmphasisVehicleListDTO emphasisVehicleListDTO = new EmphasisVehicleListDTO();

        List<EmphasisVehicleInformationDTO> emphasisVehiclePercentageList = emphasisVehicleService.getEmphasisVehiclePercentage();
        List<EmphasisVehicleInformationDTO> regionEmphasisVehicleList = emphasisVehicleService.getEmphasisVehicleByRegion();
        List<EmphasisVehicleInformationDTO> siteEmphasisVehicleList = emphasisVehicleService.getEmphasisVehicleBySite();
        List<TrafficViolationDTO> emphasisVehicleViolationList = emphasisVehicleService.getEmphasisVehicleViolation();
        List<EmphasisVehicleTimeFrameDTO> emphasisVehicleTimeFrameList = emphasisVehicleService.getEmphasisVehicleByTimeFrame();
        EmphasisVehicleNumberDTO emphasisVehicleNumber = emphasisVehicleService.getEmphasisVehicleNumber();
        List<EmphasisVehicleImgUrl> emphasisVehicleImgUrList = emphasisVehicleService.getEmphasisVehicleImg();
        emphasisVehicleListDTO.setEmphasisVehiclePercentageList(emphasisVehiclePercentageList);
        emphasisVehicleListDTO.setRegionEmphasisVehicleList(regionEmphasisVehicleList);
        emphasisVehicleListDTO.setSiteEmphasisVehicleList(siteEmphasisVehicleList);
        emphasisVehicleListDTO.setEmphasisVehicleViolationList(emphasisVehicleViolationList);
        emphasisVehicleListDTO.setEmphasisVehicleTimeFrameList(emphasisVehicleTimeFrameList);
        emphasisVehicleListDTO.setEmphasisVehicleNumber(emphasisVehicleNumber);
        emphasisVehicleListDTO.setEmphasisVehicleImgUrlList(emphasisVehicleImgUrList);
        return ResultDTO.success(emphasisVehicleListDTO);
    }
}
