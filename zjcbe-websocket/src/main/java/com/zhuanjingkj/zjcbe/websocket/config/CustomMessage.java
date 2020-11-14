package com.zhuanjingkj.zjcbe.websocket.config;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/9/3 9:21
 **/
public class CustomMessage<T> extends GenericMessage<T> {

	private T content;

	public CustomMessage(T payload) {
		super(payload);
	}

	public CustomMessage(T payload, Map<String, Object> headers) {
		super(payload, headers);
	}

	public CustomMessage(T payload, MessageHeaders headers) {
		super(payload, headers);
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public T getPayload() {
		return this.content;
	}
}
