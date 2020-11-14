package com.zhuanjingkj.zjcbe.dbengine.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @description: TransactionDataSourceContextHolder
 * @author: wangliying
 * @create: 2018-05-18
 **/
public class TransactionDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    private static Logger logger = LoggerFactory.getLogger(TransactionDataSourceContextHolder.class);
    /**
     * 使用setDataSourceType设置当前的
     * @param dataSourceType
     */
    public static void setTransactionDataSourceType(String dataSourceType) {
        Assert.notNull(dataSourceType, "TransactionDataSource is null,please set @XXXDataSource with @Transactional");
        contextHolder.set(dataSourceType.toLowerCase());
    }

    /*
     * 获取数据源
     */
    public static String getTransactionDataSourceType() {

        return contextHolder.get();
    }


    public static void clearTransactionDataSourceType() {

        contextHolder.remove();
    }
}
