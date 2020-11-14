package com.zhuanjingkj.zjcbe.utility.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;

@Component
public class IpUtils {

	public String getServerIp() {
		// 获取操作系统类型
		String sysType = System.getProperties().getProperty("os.name");
		String ip;
		if (sysType.toLowerCase().startsWith("win")) {  // 如果是Windows系统，获取本地IP地址
			String localIP = null;
			try {
				localIP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				//logger.error(e.getMessage(), e);
				System.out.println(e.getMessage());
			}
			if (localIP != null) {
				return localIP;
			}
		} else {
			ip = getIpByEthNum("eth1"); // 兼容Linux
			if (ip != null) {
				return ip;
			}
		}
		return "获取服务器IP错误";
	}

	/**
	 * 根据网络接口获取IP地址
	 *
	 * @param ethNum 网络接口名，Linux下是eth0
	 * @return
	 */
	private String getIpByEthNum(String ethNum) {
		try {
			Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (ethNum.equals(netInterface.getName())) {
					Enumeration addresses = netInterface.getInetAddresses();
					while (addresses.hasMoreElements()) {
						ip = (InetAddress) addresses.nextElement();
						if (ip != null && ip instanceof Inet4Address) {
							return ip.getHostAddress();
						}
					}
				}
			}
		} catch (SocketException e) {
			//logger.error(e.getMessage(), e);
			System.out.println(e.getMessage());
		}
		return "获取服务器IP错误";
	}

	/**
	 * 获取当前客户端请求IP
	 *
	 * @return
	 */
	public String getClientIp() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		String ipAddress;
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if (ipAddress.equals("127.0.0.1")) {
					//根据网卡取本机配置的IP
					try {
						InetAddress inet = InetAddress.getLocalHost();
						ipAddress = inet.getHostAddress();

					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
				}
			}
			//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) {
				if (ipAddress.indexOf(",") > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		} catch (Exception e) {
			ipAddress = "";
		}
		return ipAddress;
	}
}
