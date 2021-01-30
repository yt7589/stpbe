package com.zhuanjingkj.stpbe.tvis_server.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.SubmitImageDTO;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameDTO;
import com.zhuanjingkj.stpbe.tvis_server.dto.TvisAnalysisResultDTO;

import java.util.Map;

public interface IStpImageService {
    ResultDTO<SubmitImageDTO> submitImage(String cameraId, String gcxh,
                                          String mrhpt, String hphm, byte[]
                                                  imageData, String imageFile);
    ResultDTO<WsmVideoFrameDTO> getTvisAnalysisResult(long cameraId, long baseTvisJsonId, int direction);
}
