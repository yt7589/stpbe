package com.zhuanjingkj.zjcbe.utility.filter;

import com.zhuanjingkj.zjcbe.utility.list.FilterUtils;
import com.zhuanjingkj.zjcbe.utility.reflect.ReflectionUtils;
import net.logstash.logback.encoder.org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;

public class XssSqlFilterUtils {

	private static String key = "and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+";
	private static Set<String> notAllowedKeyWords = new HashSet<String>(0);
	private static String replacedString = "INVALID";

	static {
		String keyStr[] = key.split("\\|");
		for (String str : keyStr) {
			notAllowedKeyWords.add(str);
		}
	}

	/**
	 * 所有string类型的字段都进行xss过滤
	 *
	 * @return
	 */
	public static Object cleanXSSfields(Object o) {

		List<Field> fieldList = new ArrayList(Arrays.asList(o.getClass().getDeclaredFields()));
		List<Class> claszes = ReflectionUtils.getSuperClass(o.getClass());
		if (claszes != null && claszes.size() > 0) {
			for (Class clasze : claszes) {
				List<Field> fs = new ArrayList(Arrays.asList(clasze.getDeclaredFields()));
				fieldList.addAll(fs);
			}
		}

		Arrays.stream(BeanUtils.getPropertyDescriptors(o.getClass())).forEach(t ->
				{

					AntiXSSMode xssMode = AntiXSSMode.DEFAULT;

					//System.out.println("xss filter "+t.getName());
					Field field = FilterUtils.conditionFilter(fieldList, h -> h.getName().equals(t.getName()));
					if (field == null) {
						return;
					}

					XSSMode xssModeField = field.getAnnotation(XSSMode.class);
					if (xssModeField != null) {
						xssMode = xssModeField.MODE();
					}

					if (t.getPropertyType().equals(String.class)) {
						String fieldValue = "";
						try {
							fieldValue = (String) t.getReadMethod().invoke(o);
							if (!StringUtils.isEmpty(fieldValue)) {
								t.getWriteMethod().invoke(o, cleanXSS(fieldValue, xssMode));
							}

						} catch (IllegalAccessException | InvocationTargetException e) {
							//LoggerHandler.error(e, "cleanHtml failed!"+t.getName()+":"+fieldValue);
							return;
						}
					} else if (t.getPropertyType().toString().indexOf("com.fang.agent") > 0) {
						try {
							Object obj = t.getReadMethod().invoke(o);
							if (obj != null) {
								t.getWriteMethod().invoke(o, cleanXSSfields(obj));
							}

						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					} else if (t.getPropertyType().toString().indexOf("java.util.List") > 0) {
						try {
							List<Object> aa = (List<Object>) t.getReadMethod().invoke(o);
							List<Object> bb = new ArrayList<>();
							if (aa != null) {
								for (Object a : aa) {
									bb.add(cleanXSSfields(a));
								}
								t.getWriteMethod().invoke(o, bb);
							}

						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}

					}
				}
		);
		return o;
	}

	public static String cleanXSS(String valueP) {
		return cleanXSS(valueP, AntiXSSMode.DEFAULT);
	}

	public static String cleanXSS(String valueP, AntiXSSMode xssMode) {

		if (StringUtils.isEmpty(valueP) || xssMode.equals(AntiXSSMode.NONE)) {
			return valueP;
		}
		String value = "";
		if (xssMode.equals(AntiXSSMode.DEFAULT)) {
			valueP = stripXSS(valueP);
			value = valueP.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			value = StringEscapeUtils.escapeSql(value);
		}

		//value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		//value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		//value = value.replaceAll("'", "& #39;");
		//value = value.replaceAll("eval\\((.*)\\)", "");
		//value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		//value = value.replaceAll("script", "");
		//value = cleanSqlKeyWords(value);

		return value;
	}

	/**
	 * 防止xss跨脚本攻击（替换，根据实际情况调整）
	 */
	private static String stripXSS(String value) {
		if (value != null) {
			// NOTE: It's highly recommended to use the ESAPI library and
			// uncomment the following line to
			// avoid encoded attacks.
			// value = ESAPI.encoder().canonicalize(value);
			// Avoid null characters
			/** value = value.replaceAll("", ""); ***/
			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile(
					"<[\r\n| | ]*script[\r\n| | ]*>(.*?)</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid anything in a
			// src="http://www.yihaomen.com/article/java/..." type of
			// e-xpression
			scriptPattern = Pattern.compile("src[\r\n| | ]*=[\r\n| | ]*[\\\"|\\\'](.*?)[\\\"|\\\']",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<[\r\n| | ]*script(.*?)>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid e-xpression(...) expressions
			scriptPattern = Pattern.compile("e-xpression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
		}
		return value;
	}

	private static String cleanSqlKeyWords(String value) {
		String paramValue = value;
		for (String keyword : notAllowedKeyWords) {
			if (paramValue.length() > keyword.length() + 4
					&& (paramValue.contains(" " + keyword) || paramValue.contains(keyword + " ") || paramValue.contains(" " + keyword + " "))) {
				paramValue = org.springframework.util.StringUtils.replace(paramValue, keyword, replacedString);

			}
		}
		return paramValue;
	}

}
