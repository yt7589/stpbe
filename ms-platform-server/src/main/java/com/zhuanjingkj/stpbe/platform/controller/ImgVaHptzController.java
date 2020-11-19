package com.zhuanjingkj.stpbe.platform.controller;

import com.zhuanjingkj.stpbe.platform.service.ImgVaHptzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/img/va/hptz")
public class ImgVaHptzController {

    @Autowired
    private ImgVaHptzService imgVaHptzService;

    /**
     * 获取首页交通流量数据，取昨天和今天0时至24时交通流量数据
     * @param platform
     * @param version
     * @return
     */
    @GetMapping("/traffic/flow")
    public Object getTrafficFlow(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version
    ) {
        return imgVaHptzService.countTrafficFlow();
    }
}
