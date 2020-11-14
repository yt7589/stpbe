package com.zhuanjingkj.stpbe.ms_sdk_server.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.SystemInfoDTO;

public interface ISystemInfoService {
    public ResultDTO<SystemInfoDTO> getSystemVersion(String systemName);
    public String getContacts();
}
