package com.zhuanjingkj.zjcbe.utility.web;

import com.zhuanjingkj.zjcbe.commondata.baseDTO.HttpResultDTO;
import com.zhuanjingkj.zjcbe.utility.beans.HttpUtilsConfigBean;
import com.zhuanjingkj.zjcbe.utility.web.arron.HCB;
import com.zhuanjingkj.zjcbe.utility.web.arron.HttpClientUtil;
import com.zhuanjingkj.zjcbe.utility.web.arron.HttpConfig;
import com.zhuanjingkj.zjcbe.utility.web.arron.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wangliying
 * @create: 2018-06-14
 **/
@Component
@ConditionalOnBean(HttpUtilsConfigBean.class)
public class SimpleHttpUtils {

	private static Logger logger = LoggerFactory.getLogger(SimpleHttpUtils.class);

	private HCB hcb;

	public static final String CHARSET = "UTF-8";

	public SimpleHttpUtils(HttpUtilsConfigBean httpUtilsConfigBean) {
		//todo:连接池数改为配置
		try {
			hcb = HCB.custom()
					.timeout(httpUtilsConfigBean.getConnectTimeout())
					.pool(200, 200);
		} catch (HttpProcessException e) {
			e.printStackTrace();
			logger.error("HttpClientBuilder失败： 错误：" + e.getMessage() + e.getStackTrace());
		}

	}

	public HttpResultDTO get(String url, Map<String, Object> params) {
		return HttpClientUtil.get(config(url, params, CHARSET));
	}

	public HttpResultDTO get(String url, Map<String, Object> params, String charset) {
		return HttpClientUtil.get(config(url, params, charset));
	}

	/**
	 * Http Post请求（Json格式，自定义header）
	 *
	 * @param url
	 * @param charset
	 * @param headers
	 * @return
	 */
	public HttpResultDTO get(String url, Map<String, Object> params, String charset, Map<String, String> headers) {
		HttpConfig config = null;
		try {
			List<Header> headerList = new ArrayList<>();

			for (Map.Entry<String, String> entry : headers.entrySet()) {
				headerList.add(new BasicHeader(entry.getKey(), entry.getValue()));
			}
			config = HttpConfig.custom()
					.url(url)
					.encoding(charset)
					.hcb(hcb)
					.map(params)
					.headers(headerList.toArray(new Header[headers.size()]));

		} catch (HttpProcessException e) {
			e.printStackTrace();

			return null;
		}
		return HttpClientUtil.get(config);
	}

	/**
	 * Http Post请求（Json格式，自定义header）
	 *
	 * @param url
	 * @param charset
	 * @param headers
	 * @return
	 */
	public HttpResultDTO get(String url, String charset, Map<String, String> headers) {
		HttpConfig config = null;
		try {
			List<Header> headerList = new ArrayList<>();

			for (Map.Entry<String, String> entry : headers.entrySet()) {
				headerList.add(new BasicHeader(entry.getKey(), entry.getValue()));
			}
			config = HttpConfig.custom()
					.url(url)
					.encoding(charset)
					.hcb(hcb)
					.headers(headerList.toArray(new Header[headers.size()]));

		} catch (HttpProcessException e) {
			e.printStackTrace();

			return null;
		}
		return HttpClientUtil.get(config);
	}

	public HttpResultDTO post(String url, Map<String, Object> params) {
		return HttpClientUtil.post(config(url, params, CHARSET));
	}

	public HttpResultDTO post(String url, Map<String, Object> params, String charset) {
		return HttpClientUtil.post(config(url, params, charset));
	}

	public HttpResultDTO postJson(String url, String json) {
		return postJson(url, json, CHARSET);
	}

	public HttpResultDTO postJson(String url, String json, String charset) {
		HttpConfig config = null;
		try {

			List<Header> headers = new ArrayList<>();
			headers.add(new BasicHeader("Content-type", "application/json"));

			config = HttpConfig.custom()
					.url(url)
					.json(json)
					.encoding(charset)
					.hcb(hcb)
					.headers(headers.toArray(new Header[headers.size()]));
		} catch (HttpProcessException e) {
			e.printStackTrace();

			return null;
		}
		return HttpClientUtil.post(config);
	}

	/**
	 * Http Post请求（Json格式，自定义header）
	 *
	 * @param url
	 * @param params
	 * @param charset
	 * @param headers
	 * @return
	 */
	public HttpResultDTO post(String url, Map<String, Object> params, String charset, Map<String, String> headers) {
		HttpConfig config = null;
		List<Header> headerList = new ArrayList<>();
		if (!headers.containsKey("Content-type")) {
			headerList.add(new BasicHeader("Content-type", "application/json"));
		}
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			headerList.add(new BasicHeader(entry.getKey(), entry.getValue()));
		}
		config = config(url, params, charset).headers(headerList.toArray(new Header[headers.size()]));

		return HttpClientUtil.post(config);
	}

	/**
	 * Http Post请求（Json格式，自定义header）
	 *
	 * @param url
	 * @param json
	 * @param charset
	 * @param headers
	 * @return
	 */
	public HttpResultDTO postJson(String url, String json, String charset, Map<String, String> headers) {
		HttpConfig config = null;
		try {
			List<Header> headerList = new ArrayList<>();
			if (!headers.containsKey("Content-type")) {
				headerList.add(new BasicHeader("Content-type", "application/json"));
			} else {
				headers.put("Content-type", "application/json");
			}

			for (Map.Entry<String, String> entry : headers.entrySet()) {
				headerList.add(new BasicHeader(entry.getKey(), entry.getValue()));
			}
			config = HttpConfig.custom()
					.url(url)
					.json(json)
					.encoding(charset)
					.hcb(hcb)
					.headers(headerList.toArray(new Header[headers.size()]));

		} catch (HttpProcessException e) {
			e.printStackTrace();

			return null;
		}
		return HttpClientUtil.post(config);
	}

	/**
	 * .client(hcb.build())//如果只是简单实用，无需设置，会自动获取默认的client对象
	 * .hcb(hcb)//开启http连接池需要条用该方法，否则可以直接调用client(hcb.build())
	 * .inenc("utf-8")//设置请求编码，如果请求返回一直，不需要在单独设置
	 * .json("json字符串")//json方式请求的话，就不用设置map方法，当然二者可以公用
	 * .context(HttpCookies.custom().getContext())//设置cookie，用于完成携带cokie的操作
	 * .out(new FileOutputStream("保存地址"))//下载的话，设置这个方法，否则不要设置
	 * .files(new String[]{"d:/1.jpg","d:/2.xtx"})//上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
	 */
	private HttpConfig config(String url, Map<String, Object> params, String charset) {
		HttpConfig config = null;
		try {
			config = HttpConfig.custom()
					//.headers(headers)
					.url(url)
					.map(params)
					.encoding(charset)
					.hcb(hcb);
		} catch (HttpProcessException e) {
			e.printStackTrace();

		}

		return config;
	}
}
