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
    public final static String KS_AS_SFVS = "KsAsSfvs"; // Site Frequent Vehicles


    private static Map<String, Map<String, WebSocketSession>> topics = null;
    public static void initialize() {
        topics = new HashMap<>();
        topics.put(KS_SVS_LTVIS, new HashMap<>());
        topics.put(KS_AS_SFVS, new HashMap<>());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);

        System.out.println("payload: " + payload + "!");

        String user = jsonObject.getString("userId");
        String type = jsonObject.getString("type");
        String topic = jsonObject.getString("topic");
        synchronized (topics) {
            System.out.println("step 1");
            if (topics.get(topic) != null) {
                System.out.println("step 2");
                if (type.equals("sub")) {
                    System.out.println("step 3");
                    topics.get(topic).put(user, session);
                    System.out.println("step 4");
                } else {
                    System.out.println("step 5");
                    topics.get(topic).remove(user);
                    System.out.println("step 6");
                }
                System.out.println("step 7");
            }
            System.out.println("step 8");
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
        System.out.println("push 1");
        if (!topics.containsKey(topic)) {
            System.out.println("push 1.1");
            return ;
        }
        System.out.println("push 2");
        Map<String, WebSocketSession> sessions = topics.get(topic);
        System.out.println("push 3 session=" + sessions + "!");
        for (WebSocketSession sess : sessions.values()) {
            try {
                System.out.println("push 4");
                sess.sendMessage(new TextMessage(msg));
                System.out.println("push 5");
            } catch (IOException e) {
                System.out.println("push 6 exception: " + e.getMessage() + "!");
                e.printStackTrace();
            }
        }
    }
}
