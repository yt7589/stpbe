package com.zhuanjingkj.zjcbe.api.controller.analysis;

import com.zhuanjingkj.zjcbe.api.annotation.BaseAuthorize;
import com.zhuanjingkj.zjcbe.business.dto.analysis.RectifyImgRecgRstDTO;
import com.zhuanjingkj.zjcbe.business.dto.analysis.RtmpConfigResponseDTO;
import com.zhuanjingkj.zjcbe.business.dto.analysis.VehicleAnalysisResponseDTO;
import com.zhuanjingkj.zjcbe.business.dto.analysis.VideoUploadResponseDTO;
import com.zhuanjingkj.zjcbe.business.service.analysis.IVehicleAnalysisService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.rto.RectifyImgRecgRstRTO;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import com.zhuanjingkj.zjcbe.utilityredis.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "车辆识别")
@RestController
@RequestMapping(value = "/vehicle/api/analysis")
public class VehicleAnalysisController {

    @Autowired
    IVehicleAnalysisService analysisService;

    @Autowired
    RedisUtil redisUtil;

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 用户发现图片识别结果错误时向平台反映出错情况
     * @param accessToken
     * @param imageId
     * @param userJson JSON字符串，系统根据用户输入生成的JSON格式结果
     * @param userNotes 用户填写的注释信息
     * @return
     */
    @ApiOperation(value = "图片识别纠错", notes = "向服务器反馈图片识别结果有问题")
    @PostMapping(value = "/rectifyImgRecgRst0")
    @BaseAuthorize
    public ResultDTO<RectifyImgRecgRstDTO> rectifyImgRecgRst0(
            @RequestParam(value = "p") String platform,
            @RequestParam(value = "v") String version,
            @RequestHeader String accessToken,
            @RequestParam(value = "imageId") String imageId,
            @RequestParam(value = "userJson") String userJson,
            @RequestParam(value = "userNotes") String userNotes) {
        logger.info("platform=" + platform + "!");
        logger.info("version=" + version + "!");
        logger.info("accessToken=" + accessToken);
        logger.info("imageId=" + imageId + "!");
        try {
            return analysisService.rectifyImage(imageId, "", "");
        } catch (Exception ex) {
            return CustomOutputUtility.excuteFail(ex.getMessage());
        }
    }
    @ApiOperation(value = "图片识别纠错", notes = "向服务器反馈图片识别结果有问题")
    @PostMapping(value = "/rectifyImgRecgRst", produces = "application/json;charset=UTF-8")
    @BaseAuthorize
    public ResultDTO<RectifyImgRecgRstDTO> rectifyImgRecgRst(
            @RequestHeader String accessToken,
            @RequestBody RectifyImgRecgRstRTO rto) {
        logger.info("platform=" + rto.getP() + "!");
        logger.info("version=" + rto.getV() + "!");
        logger.info("accessToken=" + accessToken);
        logger.info("imageId=" + rto.getImageId() + "!");
        try {
            return analysisService.rectifyImage(rto.getImageId(), "", "");
        } catch (Exception ex) {
            return CustomOutputUtility.excuteFail(ex.getMessage());
        }
    }


    @ApiOperation(value = "图片识别", notes = "车辆识别结果入库")
    @PostMapping(value = "/getrtmpconfigurl")
    @BaseAuthorize
    public ResultDTO<RtmpConfigResponseDTO> getRtmpConfigByUrl(@RequestHeader String accessToken, String rtmpUrl) {
        try {
            System.out.println("##########  accessToken:" + accessToken + "!");
            System.out.println("##########  rtmpUrl=" + rtmpUrl + "!");
            return analysisService.getRtmpConfigByUrl(rtmpUrl);
        } catch (Exception ex) {
            logger.error(String.format("获取rtmp配置失败：%s", ex.getMessage()), ex);
            return CustomOutputUtility.excuteFail(ex.getMessage());
        }
    }

    @ApiOperation(value = "图片识别", notes = "车辆识别结果入库")
    @PostMapping(value = "/imageupload")
    @BaseAuthorize
    public ResultDTO<VehicleAnalysisResponseDTO> imgageUpload(@RequestHeader String accessToken, @RequestParam(value = "image") MultipartFile file) {
        if (file.isEmpty()) {
            CustomOutputUtility.excuteFail("上传图片不能为空！");
        }
        return analysisService.imageAnalysis(file, false);
    }

    @ApiOperation(value = "视频上传", notes = "车辆识别结果入库")
    @PostMapping(value = "/videoupload")
    @BaseAuthorize
    public ResultDTO<VideoUploadResponseDTO> videoUpload(@RequestHeader String accessToken, @RequestParam(value = "video") MultipartFile file) {
        if (file.isEmpty()) {
            CustomOutputUtility.excuteFail("上传视频不能为空！");
        }
        return analysisService.videoUpload(file);
    }

    @ApiOperation(value = "图片识别demo", notes = "前台图片车辆识别demo演示,不做数据存储")
    @PostMapping(value = "/imagedemo")
    public ResultDTO<VehicleAnalysisResponseDTO> imageDemo(@RequestParam(value = "image") MultipartFile file) {
        if (file.isEmpty()) {
            CustomOutputUtility.excuteFail("上传图片不能为空！");
        }
        return analysisService.imageAnalysis(file, true);
    }

    @ApiOperation(value = "获取rstp序号获取rtmp识别结果", notes = "获取rstp序号获取rtmp识别结果")
    @PostMapping(value = "/getanalysisbyid")
    @BaseAuthorize
    public ResultDTO<VehicleAnalysisResponseDTO> getAnalysisResult(@RequestHeader String accessToken, @RequestParam(value = "rstpId") String rstpId) {
        if (StringUtils.isBlank(rstpId)) {
            return CustomOutputUtility.excuteFail("rstp序号不能为空！");
        }
        System.out.println("##### rstpId=" + rstpId + "!");
        return analysisService.getAnalysisResult(rstpId);
    }

    @GetMapping("/redistest")
    public String test() {
        try {
            String key = "test";
            return String.format("%s:%s", key, redisUtil.get("test"));
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
