package com.zhuanjingkj.stpbe.tmdp.conf;

import com.zhuanjingkj.stpbe.tmdp.controller.TmdpWsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private TmdpWsHandler tmdpWsHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(tmdpWsHandler, "/stp").setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(tmdpWsHandler, "/stp").setAllowedOrigins("*").withSockJS();
    }
}
