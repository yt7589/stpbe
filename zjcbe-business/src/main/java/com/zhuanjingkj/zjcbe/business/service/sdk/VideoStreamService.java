package com.zhuanjingkj.zjcbe.business.service.sdk;

import com.zhuanjingkj.zjcbe.commondata.constance.AppConst;
import com.zhuanjingkj.zjcbe.commondata.constance.VehicleConst;
import com.zhuanjingkj.zjcbe.commondata.dto.BindVideoStreamRtmpDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.CreateVideoStreamDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.GetVideoStreamsDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.UnbindVideoStreamRtmpDTO;
import com.zhuanjingkj.zjcbe.commondata.po.VideoStreamPO;
import com.zhuanjingkj.zjcbe.commondata.rto.BindVideoStreamRtmpRTO;
import com.zhuanjingkj.zjcbe.commondata.rto.CreateVideoStreamRTO;
import com.zhuanjingkj.zjcbe.commondata.rto.UnbindVideoStreamRtmpRTO;
import com.zhuanjingkj.zjcbe.domain.entity.VideoStreamEntity;
import com.zhuanjingkj.zjcbe.repository.dc.DcVideoRecgRepository;
import com.zhuanjingkj.zjcbe.repository.sdk.SdkQueryDAO;
import com.zhuanjingkj.zjcbe.repository.sdk.VideoStreamRepository;
import com.zhuanjingkj.zjcbe.utility.net.TcpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VideoStreamService implements IVideoStreamService {
    @Autowired
    VideoStreamRepository videoStreamRepository;
    @Autowired
    SdkQueryDAO sdkQueryDAO;

    /**
     * 生成实时视频流，将名称和RTSP与RTMP对应关系保存到数据库中
     * @param rto
     * @return
     */
    @Override
    public CreateVideoStreamDTO createVideoStream(CreateVideoStreamRTO rto) {
        CreateVideoStreamDTO dto = new CreateVideoStreamDTO();
        List<Object> rst = getRtmpOfRtsp(rto.getRtspUrl());
        int state = (int)rst.get(0);
        String rtmpUrl = (String)rst.get(1);
        if (state != 0) {
            dto.setState(state);
            dto.setMsg(ERROR_MSGS[state]);
            return dto;
        }
        VideoStreamEntity entity = new VideoStreamEntity();
        entity.setVideoStreamName(rto.getVideoStreamName());
        entity.setRtspUrl(rto.getRtspUrl());
        entity.setRtmpUrl(rtmpUrl);
        entity.setStartTime(new Date());
        Date endTime = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            endTime = df.parse("2999-12-31 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setEndTime(endTime);
        long videoStreamId = videoStreamRepository.insertVideoStream(entity);
        dto.setState(0);
        dto.setVideoStreamId(videoStreamId);
        return dto;
    }

    private final static int DS_GET_RTMP_OF_RTSP_ERROR_1 = 1;
    private final static int DS_GET_RTMP_OF_RTSP_ERROR_2 = 2;
    private final static String[] ERROR_MSGS = {
            "获取RTMP失败：RTMP为空",
            "获取RTMP失败：RTMP不合法"
    };

    private List<Object> getRtmpOfRtsp(String rtspUrl) {
        List<Object> rst = new ArrayList<>();
        int state = 0;
        byte[] resp = TcpClient.sendRequest(AppConst.SERVER_ADDR,
                AppConst.SERVER_PORT, rtspUrl.getBytes());
        String rtmpUrl = null;
        if (resp == null) {
            state = DS_GET_RTMP_OF_RTSP_ERROR_1;
        }
        rtmpUrl = new String(resp);
        if (rtmpUrl.indexOf("rtmp://") != 0) {
            state = DS_GET_RTMP_OF_RTSP_ERROR_2;
        }
        rst.add(state);
        rst.add(rtmpUrl);
        return rst;
    }

    @Override
    public BindVideoStreamRtmpDTO bindVideoStreamRtmp(BindVideoStreamRtmpRTO rto) {
        BindVideoStreamRtmpDTO dto = new BindVideoStreamRtmpDTO();
        List<Object> rst = getRtmpOfRtsp(rto.getRtspUrl());
        int state = (int)rst.get(0);
        String rtmpUrl = (String)rst.get(1);
        if (state != 0) {
            dto.setState(state);
            dto.setMsg(ERROR_MSGS[state]);
            return dto;
        }
        VideoStreamEntity entity = new VideoStreamEntity();
        entity.setVideoStreamId(rto.getVideoStreamId());
        entity.setRtmpUrl(rtmpUrl);
        entity.setState(1);
        videoStreamRepository.updateVideoStream(entity);
        dto.setRtmpUrl(rtmpUrl);
        return dto;
    }

    @Override
    public UnbindVideoStreamRtmpDTO unbindVideoStreamRtmp(UnbindVideoStreamRtmpRTO rto) {
        UnbindVideoStreamRtmpDTO dto = new UnbindVideoStreamRtmpDTO();
        VideoStreamEntity entity = new VideoStreamEntity();
        entity.setVideoStreamId(rto.getVideoStreamId());
        entity.setRtmpUrl("");
        entity.setState(1);
        videoStreamRepository.updateVideoStream(entity);
        return dto;
    }

    @Override
    public GetVideoStreamsDTO getVideoStreams(int startIdx, int amount, int direction, int state) {
        GetVideoStreamsDTO dto = new GetVideoStreamsDTO();
        int total = sdkQueryDAO.getVideoStreamsTotal(state);
        List<VideoStreamPO> videoStreams = sdkQueryDAO.getVideoStreams(startIdx, amount, direction, state);
        dto.setVideoStreams(videoStreams);
        dto.setTotal(total);
        return dto;
    }
}
