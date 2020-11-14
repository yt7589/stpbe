package com.zhuanjingkj.zjcbe.utility.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DescriptionTag {
	//字段的描述注解
	String desc();
}
