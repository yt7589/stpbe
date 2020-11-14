package com.zhuanjingkj.zjcbe.websocket.config;

import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/9/1 16:26
 **/
@Component
public class StompUserPools {

	private static final Map<String, Principal> repo = new ConcurrentHashMap<String, Principal>();

	public void addUser(Principal principal) {
		if (principal != null) {
			repo.put(principal.getName(), principal);
		}
	}

	public void delUser(Principal principal) {
		if (principal != null) {
			repo.remove(principal.getName());
		}
	}

	public Map<String, Principal> getUsers() {
		return repo;
	}

	public int countUsers() {
		return repo.size();
	}

	public Principal find(String name) {
		return repo.get(name);
	}
}
