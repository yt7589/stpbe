package com.zhuanjingkj.zjcbe.utility.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 防止sql注入,xss攻击
 * 前端可以对输入信息做预处理，后端也可以做处理。
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private String currentUrl;

	private String body;

	public String getBody() {
		return body;
	}

	Map<String, String> params = new HashMap<>();

	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) throws IOException {
		super(servletRequest);
		currentUrl = servletRequest.getRequestURI();
		this.body = readBody(servletRequest);
	}


	public String readBody(HttpServletRequest servletRequest) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader reader = servletRequest.getReader();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

//        JSONObject jsonObject = JSONObject.parseObject(sb.toString());
//        String cleanbody=JSONObject.toJSONString(jsonObject);

		return sb.toString();
	}

	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 */
	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return XssSqlFilterUtils.cleanXSS(value);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = XssSqlFilterUtils.cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> values = super.getParameterMap();
		if (values == null) {
			return null;
		}
		Map<String, String[]> result = new HashMap<>();
		for (String key : values.keySet()) {
			String encodedKey = XssSqlFilterUtils.cleanXSS(key);
			int count = values.get(key).length;
			String[] encodedValues = new String[count];
			for (int i = 0; i < count; i++) {
				encodedValues[i] = XssSqlFilterUtils.cleanXSS(values.get(key)[i]);
			}
			result.put(encodedKey, encodedValues);
		}
		return result;
	}

	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取
	 * getHeaderNames 也可能需要覆盖
	 */
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		return XssSqlFilterUtils.cleanXSS(value);
	}


	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());
		return new ServletInputStream() {
			/**
			 * Has the end of this InputStream been reached?
			 *
			 * @return <code>true</code> if all the data has been read from the stream,
			 * else <code>false</code>
			 * @since Servlet 3.1
			 */
			@Override
			public boolean isFinished() {
				return false;
			}

			/**
			 * Can data be read from this InputStream without blocking?
			 * Returns  If this method is called and returns false, the container will
			 * invoke {@link ReadListener#onDataAvailable()} when data is available.
			 *
			 * @return <code>true</code> if data can be read without blocking, else
			 * <code>false</code>
			 * @since Servlet 3.1
			 */
			@Override
			public boolean isReady() {
				return false;
			}

			/**
			 * Sets the {@link ReadListener} for this {@link ServletInputStream} and
			 * thereby switches to non-blocking IO. It is only valid to switch to
			 * non-blocking IO within async processing or HTTP upgrade processing.
			 *
			 * @param listener The non-blocking IO read listener
			 * @throws IllegalStateException If this method is called if neither
			 *                               async nor HTTP upgrade is in progress or
			 *                               if the {@link ReadListener} has already
			 *                               been set
			 * @throws NullPointerException  If listener is null
			 * @since Servlet 3.1
			 */
			@Override
			public void setReadListener(ReadListener listener) {

			}

			@Override
			public int read() throws IOException {
				return bais.read();
			}
		};
	}

}
