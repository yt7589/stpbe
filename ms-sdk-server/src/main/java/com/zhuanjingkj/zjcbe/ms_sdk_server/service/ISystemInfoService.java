package com.zhuanjingkj.zjcbe.ms_sdk_server.service;

import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.data.dto.SystemInfoDTO;

public interface ISystemInfoService {
    public ResultDTO<SystemInfoDTO> getSystemVersion(String systemName);
    public String getContacts();
}
