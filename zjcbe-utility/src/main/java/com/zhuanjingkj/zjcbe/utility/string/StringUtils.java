package com.zhuanjingkj.zjcbe.utility.string;

import com.zhuanjingkj.zjcbe.utility.string.substring.SubString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: wangliying
 * @create: 2018-06-11
 **/
public class StringUtils {

	private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

	/**
	 * 根据自定义注解SubString截取相应长度字符串
	 *
	 * @return
	 */
	public static void fieldSubString(Object obj) {
		List<Field> fieldList = new ArrayList<>();
		Class tempClass = obj.getClass();
		while (tempClass != null) {
			fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
			tempClass = tempClass.getSuperclass();
		}
		for (Field f : fieldList) {
			String fieldName = f.getName();
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			try {
				SubString subString = f.getAnnotation(SubString.class);
				if (subString != null) {
					Method method = obj.getClass().getMethod(getMethodName);
					//get方法获取属性值
					String value = (String) method.invoke(obj);
					if (value != null && !value.equals("") && getLengthChinese(value) > subString.subStringIndex()) {
						String temp = subStrChinese(value, subString.subStringIndex());
						//set方法赋值
						Method setMethod = obj.getClass().getMethod(setMethodName, String.class);
						setMethod.invoke(obj, temp);
					}
				}
			} catch (Exception e) {
				logger.error(String.format("字符串截取失败，原因参考：%s", e.getMessage()));
			}
		}
	}

	/**
	 * 去除前后指定字符
	 *
	 * @param source 目标字符串
	 * @param beTrim 要删除的指定字符
	 * @return 删除之后的字符串
	 * 调用示例：System.out.println(trim(", ashuh  ",","));
	 */
	public static String trim(String source, String beTrim) {
		if (source == null) {
			return "";
		}
		source = source.trim(); // 循环去掉字符串首的beTrim字符
		if (source.isEmpty()) {
			return "";
		}
		String beginChar = source.substring(0, 1);
		if (beginChar.equalsIgnoreCase(beTrim)) {
			source = source.substring(1, source.length());
			beginChar = source.substring(0, 1);
		}
		// 循环去掉字符串尾的beTrim字符
		String endChar = source.substring(source.length() - 1, source.length());
		if (endChar.equalsIgnoreCase(beTrim)) {
			source = source.substring(0, source.length() - 1);
			endChar = source.substring(source.length() - 1, source.length());
		}
		return source;
	}

	/**
	 * 判断字符串是否为null或空字符串
	 *
	 * @param str 目标字符串
	 * @return 判定结果
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str != null && (strLen = str.length()) != 0) {
			for (int i = 0; i < strLen; ++i) {
				if (!Character.isWhitespace(str.charAt(i))) {
					return false;
				}
			}

			return true;
		} else {
			return true;
		}
	}

	/**
	 * 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
	 *
	 * @param str
	 * @return
	 */
	public static int getLengthChinese(String str) {
		int count = 0;
		StringBuffer sb = new StringBuffer();
		String[] ss = str.split("");
		for (int i = 0; i < ss.length; i++) {
			count += ss[i].getBytes().length > 1 ? 2 : 1;
			sb.append(ss[i]);

		}
		return count;
	}

	/**
	 * 截取带中文的字符串
	 *
	 * @param str
	 * @param len
	 * @return
	 */
	public static String subStrChinese(String str, int len) {

		if (getLengthChinese(str) <= len) {
			return str;
		}

		int count = 0;
		StringBuffer sb = new StringBuffer();
		String[] ss = str.split("");
		for (int i = 0; i < ss.length; i++) {
			count += ss[i].getBytes().length > 1 ? 2 : 1;
			sb.append(ss[i]);
			if (count >= len) {
				break;
			}
		}

		return sb.toString();
	}

	/**
	 * @param list
	 * @param separator
	 * @param <T>
	 * @return
	 */
	public static <T> String join(List<T> list, char separator) {
		return org.apache.commons.lang3.StringUtils.join(list.toArray(), separator);
	}
}
