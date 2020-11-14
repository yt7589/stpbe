package com.zhuanjingkj.zjcbe.dbengine.annotation;

import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;

import java.lang.annotation.*;

/**
 * @description: 单库连接注解
 * @author: wangliying
 * @create: 2018-04-02
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SingleDataSource {

    DBBusinessType businessType();

    DBVisitType visitType();

}
