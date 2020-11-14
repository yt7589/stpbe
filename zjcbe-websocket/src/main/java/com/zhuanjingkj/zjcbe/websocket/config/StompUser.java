package com.zhuanjingkj.zjcbe.websocket.config;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/9/1 17:31
 **/
public class StompUser implements Principal {

	private String userName;

	private String stompSession;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getName() {
		return this.userName;
	}

	@Override
	public boolean implies(Subject subject) {
		return false;
	}

	public String getStompSession() {
		return stompSession;
	}

	public void setStompSession(String stompSession) {
		this.stompSession = stompSession;
	}
}
