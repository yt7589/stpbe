package com.zhuanjingkj.stpbe.tvis_server.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.RecognizeTvisImageDTO;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameDTO;

public interface IStpImageService {
    ResultDTO<RecognizeTvisImageDTO> submitImage(String cameraId, String gcxh,
                                                 String mrhpt, String hphm, byte[]
                                                  imageData, String imageFile);
    ResultDTO<WsmVideoFrameDTO> getTvisAnalysisResult(long cameraId, long baseTvisJsonId, int direction);
}
