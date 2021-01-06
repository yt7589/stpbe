package com.zhuanjingkj.stpbe.tmdp.service;

import org.springframework.web.socket.WebSocketSession;

/**
 * 视频分析服务接口
 */
public interface IVideoAnalysisService {
    public long registerWs(WebSocketSession wss);
}
