package com.zhuanjingkj.zjcbe.dbengine.aspect;

import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DataSourceUploader;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: wangliying
 * @create: 2018-04-02
 **/
@Aspect
@Component
@Order(value = 10)
public class SingleDataSourceAspect extends BaseDataSourceAspect {

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource)")
    public void setDataSource() {

    }

    /**
     * 切点前置通知
     */
    @Before("setDataSource() ")
    public void doBefore(JoinPoint joinPoint) {
        SingleDataSource switchAnnotation = super.getAnnotation(joinPoint, SingleDataSource.class);
        DataSourceUploader.switchSingleDataSource(switchAnnotation.businessType(), switchAnnotation.visitType());
    }

    /**
     * 切点后置通知
     */
    @AfterReturning(pointcut = "setDataSource()", returning = "retValue")
    public void doAfter(JoinPoint joinPoint, Object retValue) {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
