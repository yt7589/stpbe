package com.zhuanjingkj.zjcbe.api.controller;

import com.zhuanjingkj.zjcbe.api.annotation.BaseAuthorize;
import com.zhuanjingkj.zjcbe.business.dto.sdk.GetRtmpOfRtspDTO;
import com.zhuanjingkj.zjcbe.business.service.sdk.VideoStreamService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.BindVideoStreamRtmpDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.CreateVideoStreamDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.GetVideoStreamsDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.UnbindVideoStreamRtmpDTO;
import com.zhuanjingkj.zjcbe.commondata.rto.BindVideoStreamRtmpRTO;
import com.zhuanjingkj.zjcbe.commondata.rto.CreateVideoStreamRTO;
import com.zhuanjingkj.zjcbe.commondata.rto.UnbindVideoStreamRtmpRTO;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 跟车辆识别SDK服务器进行交互
 */
@RestController
@RequestMapping(value = "/zjc/sdk")
public class SdkController {
    private static Logger logger = LoggerFactory.getLogger(SdkController.class);

    @Autowired
    VideoStreamService videoStreamService;

    @ApiOperation(value = "获取RTMP", notes = "获取RTSP链接对应的RTMP链接地址")
    @PostMapping(value = "/bindVideoStreamRtmp")
    @BaseAuthorize
    public ResultDTO<CreateVideoStreamDTO> createVideoStream(
            @RequestParam(value = "p") String platform,
            @RequestParam(value = "v") String version,
            @RequestHeader String accessToken,
            @RequestBody CreateVideoStreamRTO rto) {
        CreateVideoStreamDTO dto = videoStreamService.createVideoStream(rto);
        return CustomOutputUtility.excuteSuccess(dto);
    }

    @ApiOperation(value = "获取RTMP", notes = "获取RTSP链接对应的RTMP链接地址")
    @PutMapping(value = "/unbindVideoStreamRtmp")
    @BaseAuthorize
    public ResultDTO<UnbindVideoStreamRtmpDTO> unbindVideoStreamRtmp(
            @RequestParam(value = "p") String platform,
            @RequestParam(value = "v") String version,
            @RequestHeader String accessToken,
            @RequestBody UnbindVideoStreamRtmpRTO rto) {
        UnbindVideoStreamRtmpDTO dto = videoStreamService.unbindVideoStreamRtmp(rto);
        return CustomOutputUtility.excuteSuccess(dto);
    }

    @ApiOperation(value = "获取RTMP", notes = "获取RTSP链接对应的RTMP链接地址")
    @PutMapping(value = "/bindVideoStreamRtmp")
    @BaseAuthorize
    public ResultDTO<BindVideoStreamRtmpDTO> bindVideoStreamRtmp(
            @RequestParam(value = "p") String platform,
            @RequestParam(value = "v") String version,
            @RequestHeader String accessToken,
            @RequestBody BindVideoStreamRtmpRTO rto) {
        BindVideoStreamRtmpDTO dto = videoStreamService.bindVideoStreamRtmp(rto);
        return CustomOutputUtility.excuteSuccess(dto);
    }

    @ApiOperation(value = "获取RTMP", notes = "获取RTSP链接对应的RTMP链接地址")
    @GetMapping(value = "/getVideoStreams")
    @BaseAuthorize
    public ResultDTO<GetVideoStreamsDTO> getVideoStreams(
            @RequestParam(value = "p") String platform,
            @RequestParam(value = "v") String version,
            @RequestHeader String accessToken,
            @RequestParam(value="startIdx") int startIdx,
            @RequestParam(value="amount") int amount,
            @RequestParam(value="direction") int direction,
            @RequestParam(value="state") int state) {
        GetVideoStreamsDTO dto = videoStreamService.getVideoStreams(startIdx, amount, direction, state);
        return CustomOutputUtility.excuteSuccess(dto);
    }









    @ApiOperation(value = "获取RTMP", notes = "获取RTSP链接对应的RTMP链接地址")
    @GetMapping(value = "/getRtmpOfRtsp")
    @BaseAuthorize
    public ResultDTO<GetRtmpOfRtspDTO> getRtmpOfRtsp(
            @RequestParam(value = "p") String platform,
            @RequestParam(value = "v") String version,
            @RequestHeader String accessToken,
            @RequestParam(value="rtspUrl") String rtspUrl,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime) {
        logger.info("获取RTMP链接");
        rtspUrl = "rtsp://" + rtspUrl;
        Socket socket = null;
        int readLen = 0;
        byte[] data = new byte[1024];
        String rtmpUrl = null;
        OutputStream outStrm = null;
        InputStream inStrm = null;
        try {
            socket = new Socket("192.168.2.68", 8002);
            outStrm = socket.getOutputStream();
            outStrm.write(rtspUrl.getBytes());
            inStrm = socket.getInputStream();
            readLen = inStrm.read(data, 0, 1024);
            rtmpUrl = new String(data, 0, readLen);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStrm != null) {
                    outStrm.close();
                }
                if (inStrm != null) {
                    inStrm.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("将RTSP、RTMP以及基本信息保存到数据库中");
        GetRtmpOfRtspDTO dto = new GetRtmpOfRtspDTO();
        dto.setState(0);
        dto.setMsg("Ok");
        dto.setRtmpUrl(rtmpUrl);
        return CustomOutputUtility.excuteSuccess(dto);
    }
}
