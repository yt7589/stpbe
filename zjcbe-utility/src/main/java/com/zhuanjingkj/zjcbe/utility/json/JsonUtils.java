package com.zhuanjingkj.zjcbe.utility.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 封装fastjson工具类
 * @author: wangliying
 * @create: 2018-06-22
 * @see <a href="https://github.com/alibaba/fastjson/wiki/Quick-Start-CN">fastjson中文文档</a>
 **/
public class JsonUtils {

	public static void setDEFFAULT_DATE_FORMAT(String format) {

		JSON.DEFFAULT_DATE_FORMAT = format;
	}

	/**
	 * 反序列化为普通对象
	 *
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T extends Object> T parseObject(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	/**
	 * 带有泛型类型的反序列化
	 *
	 * @param text
	 * @param type     泛型类型
	 * @param features
	 * @param <T>
	 * @return
	 */
	public static <T extends Object> T parseObject(String text, TypeReference<T> type, Feature... features) {
		return JSON.parseObject(text, type, features);
	}

	/**
	 * 反序列化集合
	 *
	 * @param text  需要处理json字符串
	 * @param clazz 泛型类型
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> parseArray(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}

	/**
	 * 反序列化为map
	 *
	 * @param json 需要处理json字符串
	 * @return
	 */
	public static Map<String, String> parseObject(String json) {
		LinkedHashMap<String, String> jsonMap = JSON.parseObject(json, new TypeReference<LinkedHashMap<String, String>>() {
		});
		return jsonMap;
	}

	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}
}
