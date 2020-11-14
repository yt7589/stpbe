package com.zhuanjingkj.zjcbe.utility.web;

import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @description:
 * @author: wangliying
 * @create: 2018-08-02
 **/
public class RestUtils {

	/**
	 * 获取请求的唯一ID
	 * 如果请求Header中已有，则用Header中的FangRequestID，如果没有则生成唯一ID。
	 *
	 * @param request 请求Request对象
	 * @return 唯一ID
	 */
	public static String getRequestUUId(HttpServletRequest request) {
		String requestID = request.getHeader("FangRequestID");
		if (StringUtils.isBlank(requestID)) {
			requestID = UUID.randomUUID().toString().replaceAll("-", "");
		}
		return requestID;
	}

	/**
	 * 获取HttpServletRequest
	 *
	 * @return HttpServletRequest对象
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String getRequestParameter(HttpServletRequest request) {

		if (null == request) {
			return null;
		}

		String method = request.getMethod();
		String param = null;
		if (method.equalsIgnoreCase("GET")) {
			/**
			 获取?后面的字符串
			 http://127.0.0.1:8080/test?username=zhangsan&age=100
			 -->username=zhangsan&age=100
			 http://127.0.0.1:8080/test?{"username":"zhangsan"}
			 -->{"username":"zhangsan"}是json字符串
			 有了json串就可以映射成对象了
			 */
			param = request.getQueryString();
			if (param != null && Base64.isBase64(param)) {
				param = new String(Base64.decodeBase64(param), StandardCharsets.UTF_8);
			}
			System.out.println("param:" + param);
		} else {
			//todo:因ServletRequest中getReader()和getInputStream()只能调用一次，暂时不获取body参数
//            param = getBodyData(request);
//            if (Base64.isBase64(param)) {
//                param = new String(Base64.decodeBase64(param), StandardCharsets.UTF_8);
//            }
//            System.out.println("param:" + param);
		}
		return param;
	}

	//获取请求体中的字符串(POST)
	private static String getBodyData(HttpServletRequest request) {
		try {

			BufferedReader br = null;
			StringBuilder sb = new StringBuilder("");
			try {
				br = request.getReader();
				String str;
				while ((str = br.readLine()) != null) {
					sb.append(str);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != br) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return sb.toString();

		} catch (Exception e) {
			return "";
		}
	}
}
