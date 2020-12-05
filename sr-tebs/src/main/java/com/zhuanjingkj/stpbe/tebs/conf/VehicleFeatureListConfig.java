package com.zhuanjingkj.stpbe.tebs.conf;

import com.zhuanjingkj.stpbe.data.entity.VehicleFeatureList;
import com.zhuanjingkj.stpbe.tebs.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author by guoqiang
 * date on 2020.12.05
 **/

@Configuration
public class VehicleFeatureListConfig {

    @Autowired
    SelectService selectService;

    @Bean
    public VehicleFeatureList getVehicleFeatureList(){
        VehicleFeatureList vehicleFeatureList = selectService.getVehicleFeatureList();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(vehicleFeatureList.getLicensePlateColorList().size());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return vehicleFeatureList;
    }
}
