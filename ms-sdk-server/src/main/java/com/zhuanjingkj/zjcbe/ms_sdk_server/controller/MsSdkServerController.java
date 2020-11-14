package com.zhuanjingkj.zjcbe.ms_sdk_server.controller;

import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.data.dto.SystemInfoDTO;
import com.zhuanjingkj.zjcbe.ms_sdk_server.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msSdkServer")
public class MsSdkServerController {
    @Autowired
    SystemInfoService systemInfoService;

    @GetMapping("/getSystemVersion")
    public ResponseEntity<ResultDTO<SystemInfoDTO>> getSystemVersion(@RequestParam(name="systemName") String systemName) {
        ResultDTO<SystemInfoDTO> dto = systemInfoService.getSystemVersion(systemName);
        HttpHeaders hdrs = new HttpHeaders();
        hdrs.add("zjc", "forTest");
        hdrs.add("Content-Type", "application/json; charset=utf-8");
        return ResponseEntity.status(200).headers(hdrs).body(dto);
    }

    @GetMapping("/getContacts")
    public String getContacts() {
        return systemInfoService.getContacts();
    }
}
