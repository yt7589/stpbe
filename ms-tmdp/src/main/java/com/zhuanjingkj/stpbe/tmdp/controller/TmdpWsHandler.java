package com.zhuanjingkj.stpbe.tmdp.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TmdpWsHandler extends TextWebSocketHandler {
    public final static String KS_SVS_LTVIS = "ksSvsLtvis"; // Latest Traffic Violation Infos
    public final static String KS_AS_SFVS = "ksAsSfvs"; // Site Frequent Vehicles
    public final static String KS_AS_LSVS = "ksAsLsvs"; // Latest Site Vehicles


    private static Map<String, Map<String, WebSocketSession>> topics = null;
    public static void initialize() {
        topics = new HashMap<>();
        topics.put(KS_SVS_LTVIS, new HashMap<>());
        topics.put(KS_AS_SFVS, new HashMap<>());
        topics.put(KS_AS_LSVS, new HashMap<>());
    }

    public static void cleanWsSessions() {
        cleanWsSession(KS_SVS_LTVIS);
        cleanWsSession(KS_AS_SFVS);
        cleanWsSession(KS_AS_LSVS);
    }
    private static void cleanWsSession(String topic) {
        Map<String, WebSocketSession> sesss = topics.get(topic);
        synchronized (sesss) {
            for (String key : sesss.keySet()) {
                if (!sesss.get(key).isOpen()) {
                    sesss.remove(key);
                }
            }
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        String user = jsonObject.getString("userId");
        String type = jsonObject.getString("type");
        String topic = jsonObject.getString("topic");
        synchronized (topics) {
            if (topics.get(topic) != null) {
                if (type.equals("sub")) {
                    topics.get(topic).put(user, session);
                } else {
                    topics.get(topic).remove(user);
                }
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
        for (WebSocketSession sess : sessions.values()) {
            try {
                sess.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
