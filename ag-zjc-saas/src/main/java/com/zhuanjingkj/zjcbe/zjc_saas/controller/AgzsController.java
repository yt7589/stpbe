package com.zhuanjingkj.zjcbe.zjc_saas.controller;

import com.zhuanjingkj.zjcbe.zjc_saas.fcc.FccSystemInfoService;
import com.zhuanjingkj.zjcbe.zjc_saas.service.AgzsService;
import org.apache.http.protocol.ResponseDate;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
