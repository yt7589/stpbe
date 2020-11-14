package com.zhuanjingkj.zjcbe.utility.list;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @description: 筛选器工具类
 * @author: zhiwe
 * @create: 2018-10-19 11:23
 **/
public class FilterUtils {
	/**
	 * 获取集合中符合条件的对象(只取第一个)
	 *
	 * @param list      目标集合
	 * @param predicate 条件表达式
	 * @return
	 */
	public static <T> T conditionFilter(List<T> list, Predicate<T> predicate) {
		Optional<T> object = list.stream().filter(predicate).findFirst();
		return object.isPresent() ? object.get() : null;
	}


	public static <T> List<T> conditionFilterForAll(List<T> list, Predicate<T> predicate) {
		List<T> objects = list.stream().filter(predicate).collect(Collectors.toList());
		return objects;
	}

}
