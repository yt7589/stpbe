package com.zhuanjingkj.stpbe.mgqs.controller;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.mgqs.service.impl.MgqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mgq")
public class MgqController {
    @Autowired
    private MgqService mgqService;


    @GetMapping("/buildBaseReidLib")
    public ResultDTO<BaseDTO> buildBaseReidLib(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version
    ) {
        return mgqService.importDclFds();
    }
}
