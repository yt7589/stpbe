package com.zhuanjingkj.zjcbe.business.service.analysis;

import com.zhuanjingkj.zjcbe.business.dto.analysis.RectifyImgRecgRstDTO;
import com.zhuanjingkj.zjcbe.business.dto.analysis.RtmpConfigResponseDTO;
import com.zhuanjingkj.zjcbe.business.dto.analysis.VehicleAnalysisResponseDTO;
import com.zhuanjingkj.zjcbe.business.dto.analysis.VideoUploadResponseDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleIdentifyMessageDTO;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleImageMessageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IVehicleAnalysisService {

    ResultDTO<VehicleAnalysisResponseDTO> imageAnalysis(MultipartFile file, boolean isDemo);

    SimpleResultDTO saveRtmpAnalysisMessage(VehicleIdentifyMessageDTO responseDTO);

    ResultDTO<VehicleAnalysisResponseDTO> getAnalysisResult(String rstpId);

    ResultDTO<RtmpConfigResponseDTO> getRtmpConfigByUrl(String rtmpUrl);

    SimpleResultDTO saveRtmpImageMessage(VehicleImageMessageDTO imageMessageDTO);

    ResultDTO<VideoUploadResponseDTO> videoUpload(MultipartFile file);

    ResultDTO<RectifyImgRecgRstDTO> rectifyImage(String imageId, String userJson, String userNotes);

    /**
     * 获取图片JSON格式识别结果
     * @param imageId
     * @return
     */
    ResultDTO<VehicleAnalysisResponseDTO> getImageRecgJson(String imageId);
}
