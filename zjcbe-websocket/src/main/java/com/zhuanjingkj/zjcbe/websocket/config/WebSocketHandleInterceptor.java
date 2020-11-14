package com.zhuanjingkj.zjcbe.websocket.config;

import com.zhuanjingkj.zjcbe.business.service.auth.IAuthService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.util.StringUtils;

public class WebSocketHandleInterceptor implements ChannelInterceptor {

	private IAuthService authService;

	public WebSocketHandleInterceptor(IAuthService _authService) {
		this.authService = _authService;
	}

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		if (StompCommand.CONNECT.equals(accessor.getCommand())) {
			String accessToken = accessor.getFirstNativeHeader("accessToken");
			if (!StringUtils.isEmpty(accessToken)) {
				SimpleResultDTO resultDTO = authService.checkToken(accessToken);
				if (resultDTO.getSuccess() && resultDTO.getCode() == 1) {
					StompUser user = new StompUser();
					user.setUserName(accessToken);
					accessor.setUser(user);
					user.setStompSession(accessor.getSessionId());
					return message;
				}
			}
		} else {
			StompUser user = (StompUser) accessor.getUser();
			if (user != null) {
				return message;
			}
		}
		return null;
	}
}


