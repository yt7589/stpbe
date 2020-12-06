package com.zhuanjingkj.stpbe.ca_tvis.service;

import com.zhuanjingkj.stpbe.ca_tvis.dto.SaveQuestionImageDTO;
import com.zhuanjingkj.stpbe.ca_tvis.rto.SaveQuestionImageRTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;

public interface IQuestionImageService {
    public ResultDTO<SaveQuestionImageDTO> saveQuestionImage(SaveQuestionImageRTO rto);
    public boolean saveQuestionImage(String imageName, int brandId, int bmyId);
}
