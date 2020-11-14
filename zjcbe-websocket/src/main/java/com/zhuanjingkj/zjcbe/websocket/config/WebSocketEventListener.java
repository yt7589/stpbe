package com.zhuanjingkj.zjcbe.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

	private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);

	@Autowired
	private StompUserPools userPools;

	@EventListener
	public void handleConnectListener(SessionConnectedEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		StompUser user = (StompUser) accessor.getUser();
		log.info("stomp client connect: {}", user.getName());
		userPools.addUser(accessor.getUser());
	}

	@EventListener
	public void handleDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		StompUser user = (StompUser) accessor.getUser();
		log.info("stomp client disconnect: {}", user.getName());
		userPools.delUser(accessor.getUser());
	}
}
