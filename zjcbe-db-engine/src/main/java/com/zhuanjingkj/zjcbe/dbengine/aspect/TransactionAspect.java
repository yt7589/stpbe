package com.zhuanjingkj.zjcbe.dbengine.aspect;

import com.zhuanjingkj.zjcbe.dbengine.datasource.DynamicDataSourceContextHolder;
import com.zhuanjingkj.zjcbe.dbengine.datasource.TransactionDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 事务切面，Order值的设定需要大于BaseDataSourceAspect的所有子类
 */
@Aspect
@Component
@Order(value = 50)
public class TransactionAspect {


    /**
     * 定义切点
     */
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void setTransactionDataSource() {
    }

    /**
     * 切点前置通知
     */
    @Before("setTransactionDataSource()")
    public void doBefore(JoinPoint joinPoint) {
        TransactionDataSourceContextHolder.setTransactionDataSourceType(DynamicDataSourceContextHolder.getDataSourceType());
    }
}
