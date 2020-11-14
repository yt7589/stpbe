package com.zhuanjingkj.zjcbe.business.service.sdk;

import com.zhuanjingkj.zjcbe.commondata.dto.BindVideoStreamRtmpDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.CreateVideoStreamDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.GetVideoStreamsDTO;
import com.zhuanjingkj.zjcbe.commondata.dto.UnbindVideoStreamRtmpDTO;
import com.zhuanjingkj.zjcbe.commondata.rto.BindVideoStreamRtmpRTO;
import com.zhuanjingkj.zjcbe.commondata.rto.CreateVideoStreamRTO;
import com.zhuanjingkj.zjcbe.commondata.rto.UnbindVideoStreamRtmpRTO;

public interface IVideoStreamService {
    public CreateVideoStreamDTO createVideoStream(CreateVideoStreamRTO rto);
    // 将RTSP与RTMP进行绑定
    public BindVideoStreamRtmpDTO bindVideoStreamRtmp(BindVideoStreamRtmpRTO rto);
    // 将RTSP与RTMP之间的对应关系解绑
    public UnbindVideoStreamRtmpDTO unbindVideoStreamRtmp(UnbindVideoStreamRtmpRTO rto);
    // 获取实时视频流列表
    public GetVideoStreamsDTO getVideoStreams(int startIdx, int amount, int direction, int state);
}
