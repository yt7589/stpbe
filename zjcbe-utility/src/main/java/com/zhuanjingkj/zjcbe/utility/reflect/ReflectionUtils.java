package com.zhuanjingkj.zjcbe.utility.reflect;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @description: 反射相关操作工具类
 * @author: zhiwe
 * @create: 2018-08-09 14:45
 **/
@Component
public class ReflectionUtils {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 获取实体中所有字符串属性值
	 *
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public List<String> getObjectStringValue(Object object, boolean isLower) throws Exception {
		List<String> valueList = new ArrayList<>();
		if (object != null) {
			//拿到类
			Class<?> clz = object.getClass();
			//获取实现类中所有属性，返回field数组
			List<Field> fieldList = new ArrayList<>();
			while (clz != null) {
				fieldList.addAll(new ArrayList<>(Arrays.asList(clz.getDeclaredFields())));
				clz = clz.getSuperclass();
			}
			Field[] fields = new Field[fieldList.size()];
			fieldList.toArray(fields);
			for (Field field : fields) {
				if (field.getGenericType().toString().equals("class java.lang.String")) {
					try {
						Method method = object.getClass().getMethod("get" + getMethodName(field.getName()));
						//调用getter方法获取属性值
						String value = (String) method.invoke(object);
						if (value != null) {
							if (isLower) {
								valueList.add(value.toLowerCase());
							} else {
								valueList.add(value);
							}
						}
					} catch (Exception ex) {
						logger.warn("映射字段异常,异常信息:" + ex.getMessage());
					}
				}
			}
		}
		return valueList;
	}

	// 把一个字符串的第一个字母大写、效率是最高的、
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	/**
	 * 实体差异比较器（返回差异字段）
	 *
	 * @param source         源对象
	 * @param current        当前对象
	 * @param propertiesList 比较字段
	 * @param <T>            实体类型
	 * @return
	 */
	public static <T> Map<String, String> differenceComparison(T source, T current, List<String> propertiesList) {
		Map<String, String> retMap = new HashMap<>();
		try {
			Class<?> clazz = source.getClass();
			Field[] properties = clazz.getDeclaredFields();
			for (Field field : properties) {
				field.setAccessible(true);//设置访问性，反射类的方法，设置为true就可以访问private修饰的东西，否则无法访问
				String name = field.getName();
				if (propertiesList != null && propertiesList.contains(name.toLowerCase())) {
					String value1 = field.get(source).toString();
					String value2 = field.get(current).toString();
					if (!value1.equals(value2)) {
						retMap.put(name.toLowerCase(), value1);
					}
				}
			}
		} catch (Exception e) {
			return retMap;
		}
		return retMap;
	}


	/**
	 * 获取该类所有基类
	 *
	 * @param calzz
	 * @return
	 */
	public static List<Class> getSuperClass(Class calzz) {
		List<Class> listSuperClass = new ArrayList<Class>();
		Class<?> superclass = calzz.getSuperclass();
		while (superclass != null) {
			if (superclass.getName().equals("java.lang.Object")) {
				break;
			}
			listSuperClass.add(superclass);
			superclass = superclass.getSuperclass();
		}
		return listSuperClass;

	}

	/**
	 * 单个对象的某个键的值
	 *
	 * @param obj 对象
	 * @param key 键
	 * @return Object 键在对象中所对应得值 没有查到时返回空字符串
	 */
	public static Object getValueByKey(Object obj, String key) {
		// 得到类对象
		Class userCla = (Class) obj.getClass();
		/* 得到类中的所有属性集合 */
		List<Field> fieldList = new ArrayList<>();
		while (userCla != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
			fieldList.addAll(Arrays.asList(userCla.getDeclaredFields()));
			userCla = userCla.getSuperclass(); //得到父类,然后赋给自己
		}
		Field[] fields = (Field[]) fieldList.toArray(new Field[fieldList.size()]);
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			try {
				if (f.getName().equals(key)) {
					//System.out.println("单个对象的某个键的值==反射==" + f.get(obj));
					return f.get(obj);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		// 没有查到时返回空字符串
		return "";
	}

}
