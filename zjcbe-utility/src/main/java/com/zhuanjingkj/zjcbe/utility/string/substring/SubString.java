package com.zhuanjingkj.zjcbe.utility.string.substring;

import java.lang.annotation.*;

/**
 * @description: 截取字符串
 * @author: zhiwe
 * @create: 2018-06-20 16:14
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubString {
	int subStringIndex() default Integer.MAX_VALUE;
}
