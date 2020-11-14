package com.zhuanjingkj.zjcbe.system_info.service;

import org.springframework.stereotype.Service;

@Service
public class SystemInfoService implements ISystemInfoService {
    @Override
    public String getSystemVersion(String systemName) {
        return systemName + " v0.8.8";
    }

    @Override
    public String getContacts() {
        return "tom, mary";
    }
}
