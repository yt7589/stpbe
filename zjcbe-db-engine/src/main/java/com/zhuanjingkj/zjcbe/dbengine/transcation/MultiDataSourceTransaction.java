package com.zhuanjingkj.zjcbe.dbengine.transcation;

import com.zhuanjingkj.zjcbe.dbengine.datasource.DynamicDataSourceContextHolder;
import com.zhuanjingkj.zjcbe.dbengine.datasource.TransactionDataSourceContextHolder;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @description: MultiDataSourceTransaction
 * @author: wangliying
 * @create: 2018-05-14
 * 参考SpringManagedTransaction，继承Transaction接口
 * @see org.mybatis.spring.transaction.SpringManagedTransaction
 **/
public class MultiDataSourceTransaction implements Transaction {

	private final DataSource dataSource;

	private Connection mainConnection;

	private String mainDatabaseIdentification;

	private ConcurrentMap<String, Connection> otherConnectionMap;

	private boolean isConnectionTransactional;

	private boolean autoCommit;

	private static Logger logger = LoggerFactory.getLogger(MultiDataSourceTransaction.class);

	public MultiDataSourceTransaction(DataSource dataSource) {
		this.dataSource = dataSource;
		otherConnectionMap = new ConcurrentHashMap<>();

		if (TransactionDataSourceContextHolder.getTransactionDataSourceType() != null) {
			mainDatabaseIdentification = TransactionDataSourceContextHolder.getTransactionDataSourceType();
		} else {
			mainDatabaseIdentification = DynamicDataSourceContextHolder.getDataSourceType();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Connection getConnection() throws SQLException {
		String databaseIdentification = DynamicDataSourceContextHolder.getDataSourceType();

		if (databaseIdentification.equals(mainDatabaseIdentification)) {
			if (mainConnection != null) {
				return mainConnection;
			} else {
				openMainConnection();
				mainDatabaseIdentification = databaseIdentification;
				return mainConnection;
			}
		} else {
			if (!otherConnectionMap.containsKey(databaseIdentification)) {
				try {
					Connection conn = dataSource.getConnection();
					otherConnectionMap.put(databaseIdentification, conn);
				} catch (SQLException ex) {
					throw new CannotGetJdbcConnectionException("Could not get JDBC Connection", ex);
				}
			}
			return otherConnectionMap.get(databaseIdentification);
		}

	}

	private void openMainConnection() throws SQLException {
		this.mainConnection = DataSourceUtils.getConnection(this.dataSource);
		this.autoCommit = this.mainConnection.getAutoCommit();
		this.isConnectionTransactional = DataSourceUtils.isConnectionTransactional(this.mainConnection, this.dataSource);

		//if (logger.isDebugEnabled()) {
		logger.debug(
				"JDBC Connection ["
						+ this.mainConnection
						+ "] will"
						+ (this.isConnectionTransactional ? " " : " not ")
						+ "be managed by Spring");
		//}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void commit() throws SQLException {
		if (this.mainConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
			//if (logger.isDebugEnabled()) {
			logger.debug("Committing JDBC Connection [" + this.mainConnection + "]");
			//}
			this.mainConnection.commit();
			for (Connection connection : otherConnectionMap.values()) {
				connection.commit();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback() throws SQLException {
		TransactionDataSourceContextHolder.clearTransactionDataSourceType();
		if (this.mainConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
			//if (logger.isDebugEnabled()) {
			logger.debug("Rolling back JDBC Connection [" + this.mainConnection + "]");
			//}
			this.mainConnection.rollback();
			for (Connection connection : otherConnectionMap.values()) {
				connection.rollback();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws SQLException {
		TransactionDataSourceContextHolder.clearTransactionDataSourceType();
		DataSourceUtils.releaseConnection(this.mainConnection, this.dataSource);
		for (Connection connection : otherConnectionMap.values()) {
			DataSourceUtils.releaseConnection(connection, this.dataSource);
		}
	}

	@Override
	public Integer getTimeout() throws SQLException {
		return null;
	}
}
