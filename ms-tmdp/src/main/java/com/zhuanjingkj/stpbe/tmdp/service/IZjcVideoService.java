package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IZjcVideoService {

    ResultDTO<String> uploadVideo(MultipartFile file, Integer consumerId);

    ResultDTO<String> bindVideo(String rtspUrl, String rtspName, String position);

    ResultDTO<String> getVideoUrl(String fileName);
}
