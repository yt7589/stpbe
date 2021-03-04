package com.zhuanjingkj.stpbe.tmdp.task;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameDTO;
import com.zhuanjingkj.stpbe.data.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VideoAnalysisTask implements Runnable {
    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    private static List<String> streamIds = new ArrayList<>();
    private static Map<String, List<WebSocketSession>> streamWsss = new HashMap<>();
    private static Map<String, CameraVehicleRecordVO> cutVehs = new HashMap<>();
    private final static Logger logger = LoggerFactory.getLogger(VideoAnalysisTask.class);
    private static long wsmVfvvIdx = 0;
    private final static long VAT_INTERVAL = 50; // 每*毫秒运行一次

    private long cameraId;

    public void run() {
        cameraId = 1;
        while (true) {
            runVideoAnalysisTask();
            try {
                Thread.sleep(VAT_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //@Async("tmdpPool")
    //@Scheduled(cron = "*/1 * * * * ?")
    //
    private static long prevTime = 0;
    public void runVideoAnalysisTask() {
        prevTime = System.currentTimeMillis();
        WsmVideoFrameDTO vfv = null;
        for (String streamId : streamIds) {
            vfv = TvisUtil.getTvisVideoAnalysisResult(tvisJsonMapper, streamIds, cutVehs, Long.parseLong(streamId));
            List<WebSocketSession> wsss = streamWsss.get("" + streamId);
            for (WebSocketSession wss : wsss) {
                if (wss.isOpen()) {
                    try {
                        wss.sendMessage(new TextMessage(JSONObject.toJSONString(vfv)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        wss.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            wsss.removeIf(wi->{return !wi.isOpen();}); // 移除所有关闭的WebSocket
        }
    }

    /**
     *
     * @param streamId
     * @param wss
     */
    public static void addStream(long streamId, WebSocketSession wss) {
        String streamIdKey = "" + streamId;
        if (!streamIds.contains(streamIdKey)) {
            streamIds.add(streamIdKey);
        }
        if (streamWsss.get("" + streamId) != null) {
            streamWsss.get(""+streamId).add(wss);
        } else {
            List<WebSocketSession> wsss = new ArrayList<>();
            wsss.add(wss);
            streamWsss.put("" + streamId, wsss);
        }
    }
}
