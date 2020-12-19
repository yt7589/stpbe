package com.zhuanjingkj.stpbe.tmdp.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TmdpWsHandler extends TextWebSocketHandler {
    private static Map<String, WebSocketSession> users = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        users.put((String)jsonObject.get("user"), session);
        System.out.println("receive: " + payload + "!");
        //session.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
    }

    public void pushMessage(String user, String msg) {
        WebSocketSession session = users.get(user);
        try {
            session.sendMessage(new TextMessage(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pushLtvis(String msg) {
        for (WebSocketSession sess : users.values()) {
            try {
                sess.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
