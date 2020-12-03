package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.data.dto.CreateRtspBindDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.service.ITvisSdkService;
import org.springframework.stereotype.Service;

@Service
public class TvisSdkService implements ITvisSdkService {
    @Override
    public ResultDTO<CreateRtspBindDTO> createRtspBind(String rtspUrl) {
        ResultDTO<CreateRtspBindDTO> dto = new ResultDTO<>();
        CreateRtspBindDTO data = new CreateRtspBindDTO();
        data.setStreamId("1008");
        dto.setData(data);
        return dto;
    }

    @Override
    public void deleteRtspBind(String rtspUrl) {

    }
}
