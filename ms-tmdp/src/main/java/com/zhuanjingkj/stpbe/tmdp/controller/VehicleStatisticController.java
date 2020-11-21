package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.*;
import com.zhuanjingkj.stpbe.tmdp.service.VehicleStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.20
 **/

@RestController
@RequestMapping("/vehicle/statistic")
public class VehicleStatisticController {

    @Autowired
    private VehicleStatisticService vehicleStatisticService;

    @GetMapping("")
    public ResultDTO<VehicleStatisticListDTO> getDkMain() {
        List<VehicleDistributionDTO> vehicleDistributionList = vehicleStatisticService.getVehicleDistribution();

        List<VehicleTypeDTO> vehicleTypeList = vehicleStatisticService.getVehicleType();

        List<TimeFrameNumberDTO> timeFrameNumberList = vehicleStatisticService.getTimeFrameNumber();

        List<VehicleTypeNumberDTO> vehicleTypeNumberList = vehicleStatisticService.getVehicleTypeNumber();

        List<VehicleStatisticDTO> streetList = vehicleStatisticService.getVehicleStatisticByStreet();

        List<VehicleStatisticDTO> regionList = vehicleStatisticService.getVehicleStatisticByRegion();

        VehiclePassedNumberDTO vehiclePassedNumber = vehicleStatisticService.getVehiclePassedNumber();

        VehicleStatisticListDTO vehicleDistributionListDTO = new VehicleStatisticListDTO();
        vehicleDistributionListDTO.setVehicleDistributionList(vehicleDistributionList);
        vehicleDistributionListDTO.setVehicleTypeList(vehicleTypeList);
        vehicleDistributionListDTO.setTimeFrameNumberList(timeFrameNumberList);
        vehicleDistributionListDTO.setVehicleTypeNumberList(vehicleTypeNumberList);
        vehicleDistributionListDTO.setStreetList(streetList);
        vehicleDistributionListDTO.setRegionList(regionList);
        vehicleDistributionListDTO.setVehiclePassedNumber(vehiclePassedNumber);
        return ResultDTO.success(vehicleDistributionListDTO);
    }
}
