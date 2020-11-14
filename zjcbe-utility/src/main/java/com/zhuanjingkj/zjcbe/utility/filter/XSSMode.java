package com.zhuanjingkj.zjcbe.utility.filter;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XSSMode {

	AntiXSSMode MODE();
}
