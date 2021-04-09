package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.vo.ZjcVideoVO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.ZjcVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value ="/zjc/video/")
@CrossOrigin(origins = "*")
public class ZjcVideoController {

    @Autowired
    private ZjcVideoService videoService;

    /**
     * 视频上传
     * @param platform
     * @param version
     * @param file
     * @param consumerId
     * @return
     */
    @PostMapping(value = "/uploadVideo")
    public ResultDTO<ZjcVideoVO> uploadVideo(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "consumerId", required = false) Integer consumerId
    ) {
        return videoService.uploadVideo(file, consumerId);
    }

    /**
     * 实时流设置
     * @param platform
     * @param version
     * @param rtspUrl
     * @param rtspName
     * @param position
     * @return
     */
    @PostMapping(value = "/bindVideo")
    public ResultDTO<String> bindVideo(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "rtspUrl", required = false) String rtspUrl,
            @RequestParam(name = "rtspName", required = false) String rtspName,
            @RequestParam(name = "position", required = false) String position
    ) {
        return videoService.bindVideo(rtspUrl, rtspName, position);
    }

    @GetMapping(value = "/getVideoUrl")
    public ResultDTO<String> getVideoUrl(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "fileName", required = false) String fileName
    ) {
        return videoService.getVideoUrl(fileName);
    }

}
