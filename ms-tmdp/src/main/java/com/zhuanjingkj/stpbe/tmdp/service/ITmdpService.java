package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.RecognizeTvisImageDTO;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameDTO;

public interface ITmdpService {
    ResultDTO<WsmVideoFrameDTO> getTvisAnalysisResult(long cameraId, long baseTvisJsonId, int direction);
    public ResultDTO<RecognizeTvisImageDTO> recognizeTvisImage(String cameraId, String gcxh,
                                                               String mrhpt, String hphm,
                                                               byte[] imageData, String imageFile);
}
