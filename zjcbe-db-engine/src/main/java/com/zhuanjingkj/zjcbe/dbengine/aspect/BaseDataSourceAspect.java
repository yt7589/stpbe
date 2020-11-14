package com.zhuanjingkj.zjcbe.dbengine.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @description: 切点基类(获取方法的声明，得到传入参数)
 * @author: wangliying
 * @create: 2018-04-02
 **/
abstract class BaseDataSourceAspect {

    <T extends Annotation> T getAnnotation(JoinPoint joinPoint, Class<T> annotationClass) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(annotationClass);
    }
}
