package com.zhuanjingkj.zjcbe.utility.config;

/**
 * @description: 网络请求超时
 * @author: wangliying
 * @create: 2018-06-12
 **/
public class CommonResourceServer {

	public static Integer getConnectTimeout() {
		return 5000;
	}

	public static Integer getSocketTimeout() {
		return 60000;
	}

}
