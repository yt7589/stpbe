package com.zhuanjingkj.zjcbe.websocket.config;

import com.zhuanjingkj.zjcbe.business.service.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	IAuthService authService;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/vehicle/stomp").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//客户端发送消息的请求前缀
		registry.setApplicationDestinationPrefixes("/app");
		//客户端订阅消息的请求前缀，topic一般用于广播推送，queue用于点对点推送
		registry.enableSimpleBroker("/topic", "/queue");
		//服务端通知客户端的前缀，可以不设置，默认为user
		registry.setUserDestinationPrefix("/user");
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// 添加拦截器，处理客户端发来的请求
		registration.interceptors(new WebSocketHandleInterceptor(authService));
	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		// TODO 数据加密
		return false;
	}
}
