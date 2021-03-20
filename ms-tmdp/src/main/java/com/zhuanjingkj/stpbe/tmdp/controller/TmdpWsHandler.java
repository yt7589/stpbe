package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.tmdp.service.impl.VideoAnalysisService;
import com.zhuanjingkj.stpbe.tmdp.task.VideoAnalysisTask;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

@Component
public class TmdpWsHandler extends TextWebSocketHandler {
    public final static String KS_SVS_LTVIS = "ksSvsLtvis"; // Latest Traffic Violation Infos
    public final static String KS_AS_SFVS = "ksAsSfvs"; // Site Frequent Vehicles
    public final static String KS_AS_LSVS = "ksAsLsvs"; // Latest Site Vehicles
    public final static String KS_RSS_SFVS = "ksRssSfvs"; // 路段监控》现场频繁车辆
    public final static String KS_RSS_LSVS = "ksRssLsvs"; // 路段监控》最新现场车辆
    // WebSocket消息类型
    public final static String WMT_RR_SPFX = "wmtRrSpfx"; // 路网实况=》视频分析
    public final static String KS_RR_SPFX = "ksRrSpfx";
    public final static String WSS_ID = "wssId";

    @Autowired
    private VideoAnalysisService videoAnalysisService;
    private static Map<String, Map<String, WebSocketSession>> topics = null;


    public static void initialize() {
        topics = new HashMap<>();
        topics.put(KS_SVS_LTVIS, new HashMap<>());
        topics.put(KS_AS_SFVS, new HashMap<>());
        topics.put(KS_AS_LSVS, new HashMap<>());
        topics.put(KS_RSS_SFVS, new HashMap<>());
        topics.put(KS_RSS_LSVS, new HashMap<>());
        // 路网实况=》视频分析
        topics.put(KS_RR_SPFX, new HashMap<>());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException, JSONException {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        String user = jsonObject.getString("userId");
        String type = jsonObject.getString("type");
        String topic = jsonObject.getString("topic");
        String action = "";
        if (jsonObject.has("action")) {
            action = jsonObject.getString("action");
        }
        synchronized (topics) {
            if (topics.get(topic) != null) {
                if (type.equals("sub")) {
                    topics.get(topic).put(user, session);
                } else {
                    topics.get(topic).remove(user);
                }
            }
            if (StringUtils.isNotBlank(type) && type.equals(WMT_RR_SPFX)) {
                long wssId = videoAnalysisService.registerWs(session);
                long streamId = jsonObject.getLong("streamId");
                if (action.equals("unsub")) {
                    System.out.println("##### 取消实时视频发送 ！！！！！！！！！！");
                    VideoAnalysisTask.removeStream(streamId, session);
                } else {
                    System.out.println("##### 请求实时视频发送 ！！！！！！！！！！");
                    VideoAnalysisTask.addStream(streamId, session);
                    JSONObject jo = new JSONObject();
                    jo.put("code", 0);
                    jo.put("type", 101);
                    jo.put("videoUrl", PropUtil.getValue("video_url_rtmp") + streamId + "");
                    jo.put("test", "测试");
                    session.sendMessage(new TextMessage(jo.toString()));
                }
                System.out.println("### 建立视频分析WebSocket连接...wssId=" + wssId + "; streamId=" + streamId + "!");
            }
        }
        System.out.println("receive: " + payload + "!");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
    }

    public void pushMessage(String user, String msg) {
        /*WebSocketSession session = users.get(user);
        try {
            session.sendMessage(new TextMessage(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void pushWsMsg(String topic, String msg) {
        if (!topics.containsKey(topic)) {
            return ;
        }
        Map<String, WebSocketSession> sessions = topics.get(topic);
        WebSocketSession sess = null;
        Set<String> keys = sessions.keySet();
        for (String key : keys) {
            sess = sessions.get(key);
            try {
                sess.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                System.out.println("TmdpWsHandler.pushWsMsg exception1: " + e.getMessage() + "!");
                if (!sess.isOpen()) {
                    synchronized (sessions) {
                        sessions.remove(key);
                        System.out.println("关闭Websocket session1...");
                    }
                }
            } catch (Exception ex) {
                System.out.println("TmdpWsHandler.pushWsMsg exception2: " + ex.getMessage() + "!");
                if (!sess.isOpen()) {
                    synchronized (sessions) {
                        sessions.remove(key);
                        System.out.println("关闭Websocket session2...");
                    }
                }
            }
        }
    }
}
