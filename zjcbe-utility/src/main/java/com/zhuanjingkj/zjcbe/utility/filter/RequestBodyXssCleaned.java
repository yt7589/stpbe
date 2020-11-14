package com.zhuanjingkj.zjcbe.utility.filter;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBodyXssCleaned {
}
