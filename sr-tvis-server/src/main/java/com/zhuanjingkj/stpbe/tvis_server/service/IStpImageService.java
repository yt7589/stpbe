package com.zhuanjingkj.stpbe.tvis_server.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.SubmitImageDTO;

import java.util.Map;

public interface IStpImageService {
    ResultDTO<SubmitImageDTO> submitImage(String cameraId, String gcxh, String mrhpt, String hphm, byte[] imageData);
}
