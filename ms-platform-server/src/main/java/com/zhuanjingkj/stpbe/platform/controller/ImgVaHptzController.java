package com.zhuanjingkj.stpbe.platform.controller;

import com.zhuanjingkj.stpbe.platform.service.ImgVaHptzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/img/va/hptz")
public class ImgVaHptzController {

    @Autowired
    private ImgVaHptzService imgVaHptzService;

    @GetMapping("/traffic/flow")
    public Object getTrafficFlow() {
        return imgVaHptzService.countTrafficFlow();
    }
}
