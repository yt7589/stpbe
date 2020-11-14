package com.zhuanjingkj.zjcbe.utility.reflect;

import com.zhuanjingkj.zjcbe.utility.config.ChangeScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

public class SingletonUtils<T> {

	private static Logger logger = LoggerFactory.getLogger(SingletonUtils.class);

	public static <T> boolean isSameParams(T t, Map<String, Object> map) {

		boolean flag = true;
		try {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				//查找对象中该变量名的变量值
				Field fieldmember = t.getClass().getDeclaredField(entry.getKey());
				fieldmember.setAccessible(true);
				Object objValue = fieldmember.get(t);

				if (fieldmember.getGenericType().toString().indexOf("com.fang.agent") >= 0) {
					//判断该类是否继承抽象类ChangeScope
					boolean isFather = ChangeScope.class.isAssignableFrom(entry.getValue().getClass());

					logger.info("----isSameParams isFather:" + isFather);

					if (isFather) {
						ChangeScope changeScope = (ChangeScope) entry.getValue();
						logger.info("----isSameParams isChange:" + changeScope.isChange());

						if (changeScope.isChange()) {
							if (changeScope.getClassNames().contains(t.getClass().toString())) {
								logger.info("----isSameParams ClassName contains:" + t.getClass().toString());
								return true;
							}

							logger.info("----isSameParams ClassName not contains:" + t.getClass().toString());
							changeScope.addClassNames(t.getClass().toString());
							return false;
						}
					}
				}

				if (!objValue.equals(entry.getValue())) {
					flag = false;
				}

			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}

		return flag;
	}


}
