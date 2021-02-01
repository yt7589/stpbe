package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.tmdp.service.impl.SmDcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * System data cleaning 数据清空
 */
@RestController
@RequestMapping(value ="/sm")
@CrossOrigin(origins = "*")
public class SmDcController {

    @Autowired
    private SmDcService smDcService;

    @RequestMapping(value = "/dc")
    public void dataClean() {
        smDcService.trkc(); //本日数据清空
        smDcService.mrkc(); //本月数据清空
    }
}
