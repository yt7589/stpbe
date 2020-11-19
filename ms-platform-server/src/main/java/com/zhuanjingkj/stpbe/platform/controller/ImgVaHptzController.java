package com.zhuanjingkj.stpbe.platform.controller;

import com.zhuanjingkj.stpbe.platform.bo.ResponseData;
import com.zhuanjingkj.stpbe.platform.bo.TrafficFlowBO;
import com.zhuanjingkj.stpbe.platform.service.ImgVaHptzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/img/va/hptz")
public class ImgVaHptzController {

    @Autowired
    private ImgVaHptzService imgVaHptzService;

    @GetMapping("/traffic/flow")
    public ResponseData< List<TrafficFlowBO>> getTrafficFlow() {
        //return ResponseData.success(imgVaHptzService.countTrafficFlow());
        return null;
    }
}
