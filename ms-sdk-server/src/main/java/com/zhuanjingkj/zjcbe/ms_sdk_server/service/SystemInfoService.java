package com.zhuanjingkj.zjcbe.ms_sdk_server.service;

import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.data.dto.SystemInfoDTO;
import org.springframework.stereotype.Service;

@Service
public class SystemInfoService implements ISystemInfoService {
    @Override
    public ResultDTO<SystemInfoDTO> getSystemVersion(String systemName) {
        ResultDTO<SystemInfoDTO> dto = new ResultDTO<>();
        SystemInfoDTO data = new SystemInfoDTO();
        data.setSystemName(systemName + "_sdk");
        data.setVersion("wxs v0.0.1");
        dto.setCode(0);
        dto.setMsg("");
        dto.setData(data);
        return dto;
    }

    @Override
    public String getContacts() {
        return null;
    }
}
