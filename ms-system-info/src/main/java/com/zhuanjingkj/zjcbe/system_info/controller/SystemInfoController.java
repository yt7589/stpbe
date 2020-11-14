package com.zhuanjingkj.zjcbe.system_info.controller;

import com.zhuanjingkj.zjcbe.system_info.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/systemInfo")
public class SystemInfoController {
    @Autowired
    SystemInfoService systemInfoService;

    @GetMapping("/getSystemVersion")
    public String getSystemVersion(@RequestParam(name="systemName") String systemName) {
        return systemInfoService.getSystemVersion(systemName);
    }

    @GetMapping("/getContacts")
    public String getContacts() {
        return systemInfoService.getContacts();
    }
}
