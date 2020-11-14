package com.zhuanjingkj.zjcbe.utility.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: wangliying
 * @create: 2018-06-12
 **/
@Configuration
public class HttpUtilsConfigBean {

	@Value("${connectTimeout:3000}")
	private int connectTimeout;

	@Value("${socketTimeout:60000}")
	private int socketTimeout;

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}
}
