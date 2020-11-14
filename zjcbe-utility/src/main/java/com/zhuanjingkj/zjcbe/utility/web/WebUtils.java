package com.zhuanjingkj.zjcbe.utility.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: lzj
 * @create: 2019/7/11 10:16
 **/
@Component
public class WebUtils {

	private static final String UNKNOWN = "unknown";
	private static final String IpAddressPattern = "(([01]?[\\d]{1,2})|(2[0-4][\\d])|(25[0-5]))(\\.(([01]?[\\d]{1,2})|(2[0-4][\\d])|(25[0-5]))){3}";

	/**
	 * 获取客户端的真实ip
	 *
	 * @return
	 */
	private String getIpAddress() {
		HttpServletRequest request = getHttpServletRequest();
		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String[] ipAddressArray = ip.split(",");
		for (String str : ipAddressArray) {
			Pattern pattern = Pattern.compile(IpAddressPattern);
			Matcher isIpAddress = pattern.matcher(str);
			if (isIpAddress.matches()) {
				ip = str;
				break;
			}
		}
		return ip;
	}

	/*
	 * 获取客户端的header
	 *
	 * @param request
	 * @return
	 * */
	public Map<String, String> getHeader() {
		HttpServletRequest request = getHttpServletRequest();
		Map<String, String> headerMap = new HashMap<String, String>();
		try {
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);
				headerMap.put(key, value);
			}
		} catch (Exception ex) {

		}
		return headerMap;
	}

	/*
	 * 获取客户端的参数
	 *
	 * @param request
	 * @return
	 * */
	public Map<String, String> getParas() {
		HttpServletRequest request = getHttpServletRequest();
		Map<String, String> paraMap = new HashMap<String, String>();
		try {
			Enumeration<String> paraNames = request.getParameterNames();
			while (paraNames.hasMoreElements()) {
				String thisName = paraNames.nextElement().toString();
				String thisValue = request.getParameter(thisName);
				paraMap.put(thisName, thisValue);
			}
		} catch (Exception ex) {

		}
		return paraMap;
	}


	public String getReqHost() {
		HttpServletRequest request = getHttpServletRequest();
		String host = request.getHeader("x-forwarded-host");

		if (host == null || host.length() == 0 || UNKNOWN.equalsIgnoreCase(host)) {
			host = request.getRequestURL().toString()
					.replaceAll(request.getRequestURI().toString(), "")
					.replaceAll("http://", "")
					.replaceAll("https://", "");
		}
		return host;
	}

	public String getReqUrl() {
		HttpServletRequest request = getHttpServletRequest();
		String reqUrl = "";
		if (request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern") != null) {
            reqUrl = request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern").toString();
        } else {
            reqUrl = request.getServletPath();
        }

		if (request.getHeader("x-forwarded-prefix") != null) {
            reqUrl = request.getHeader("x-forwarded-prefix").toString() + reqUrl;
        }

		return reqUrl;
	}


	public HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return servletRequestAttributes.getRequest();
	}

}
