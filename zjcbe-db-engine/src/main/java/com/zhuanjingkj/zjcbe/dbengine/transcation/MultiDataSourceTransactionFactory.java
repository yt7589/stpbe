package com.zhuanjingkj.zjcbe.dbengine.transcation;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import javax.sql.DataSource;

/**
 * @description: MultiDataSourceTransactionFactory
 * @author: wangliying
 * @create: 2018-05-14
 **/
public class MultiDataSourceTransactionFactory extends SpringManagedTransactionFactory {
    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new MultiDataSourceTransaction(dataSource);
    }
}
