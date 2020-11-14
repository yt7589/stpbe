package com.zhuanjingkj.zjcbe.api.controller;

import com.zhuanjingkj.zjcbe.api.annotation.BaseAuthorize;
import com.zhuanjingkj.zjcbe.business.dto.analysis.VehicleAnalysisResponseDTO;
import com.zhuanjingkj.zjcbe.business.dto.dc.GetUserVideosDTO;
import com.zhuanjingkj.zjcbe.business.dto.dc.QueryImageRecgDatasDTO;
import com.zhuanjingkj.zjcbe.business.service.analysis.IVehicleAnalysisService;
import com.zhuanjingkj.zjcbe.business.service.dc.IImageRecgDatasService;
import com.zhuanjingkj.zjcbe.business.service.dc.IVideoRecgDataService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "数据中心控制器类")
@RestController
@RequestMapping(value = "/zjc/dc")
public class DataCenterController {
    private static Logger logger = LoggerFactory.getLogger(DataCenterController.class);
    //
    @Autowired
    IImageRecgDatasService imageRecgDatasService;
    @Autowired
    IVehicleAnalysisService vehicleAnalysisService;
    @Autowired
    IVideoRecgDataService videoRecgDataService;

    @ApiOperation(value = "获取指定用户图片识别列表", notes = "用户所有图片识别记录")
    @GetMapping(value = "/queryImageRecgDatas")
    @BaseAuthorize
    public ResultDTO<QueryImageRecgDatasDTO> queryImageRecgDatas(
            @RequestParam(value = "p") String platform,
            @RequestParam(value = "v") String version,
            @RequestHeader String accessToken,
            @RequestParam(value="startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "startIdx") int startIdx,
            @RequestParam(value = "amount") int amount,
            @RequestParam(value = "direction") int direction) {
        logger.info("查询指定用户图片识别数据：startIdx=" + startIdx +
                "; amount=" + amount + "; direction=" + direction +
                "; accessToken=" + accessToken + "!");
        startTime = "1970-01-01 00:00:00";
        endTime = "2999-12-31 11:59:59";
        QueryImageRecgDatasDTO dto = imageRecgDatasService.
                queryImageRecgDatas(accessToken,
                startTime, endTime,
                startIdx, amount, direction);
        return CustomOutputUtility.excuteSuccess(dto);
    }

    @ApiOperation(value = "获取指定用户图片识别列表", notes = "用户所有图片识别记录")
    @GetMapping(value = "/getImageRecgJson")
    @BaseAuthorize
    public ResultDTO<VehicleAnalysisResponseDTO> getImageRecgJson(
            @RequestParam(value = "p") String platform,
            @RequestParam(value = "v") String version,
            @RequestHeader String accessToken,
            @RequestParam(value="imageId") String imageId) {
        return vehicleAnalysisService.getImageRecgJson(imageId);
    }

    @ApiOperation(value = "获取指定用户视频（SteamId）列表", notes = "视频（steamId）")
    @GetMapping(value = "/getUserVideos")
    @BaseAuthorize
    public ResultDTO<GetUserVideosDTO> getUserVideos(
            @RequestParam(value = "p") String platform,
            @RequestParam(value = "v") String version,
            @RequestHeader String accessToken) {
        GetUserVideosDTO dto = videoRecgDataService.getUserVideos(accessToken);
        return CustomOutputUtility.excuteSuccess(dto);
    }
}
