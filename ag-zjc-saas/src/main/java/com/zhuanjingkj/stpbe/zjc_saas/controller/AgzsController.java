package com.zhuanjingkj.stpbe.zjc_saas.controller;

import com.zhuanjingkj.stpbe.zjc_saas.service.AgzsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agZjcSaas")
public class AgzsController {
    @Autowired
    AgzsService agzsService;
    //@Autowired
    //FccSystemInfoService fccSystemInfoService;

    @GetMapping("/callGetSystemVersion")
    public String callGetSystemVersion(@RequestParam(name="systemName") String systemName) {
        return "temp";
        //return "Feign: " + fccSystemInfoService.getSystemVersion(systemName);
        //return agzsService.callGetSystemVersion(systemName);
    }

    @GetMapping("/callGetContacts")
    public String callGetContacts() {
        return agzsService.callGetContacts();
    }
}
