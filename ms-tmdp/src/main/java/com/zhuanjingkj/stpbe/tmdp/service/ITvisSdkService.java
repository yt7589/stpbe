package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.CreateRtspBindDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;

public interface ITvisSdkService {
    public ResultDTO<CreateRtspBindDTO> createRtspBind(String rtspUrl);
    public void deleteRtspBind(String rtspUrl);
}
