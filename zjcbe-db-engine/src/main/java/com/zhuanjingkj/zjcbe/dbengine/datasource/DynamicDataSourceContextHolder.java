package com.zhuanjingkj.zjcbe.dbengine.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 动态改变数据源上下文的名称
 * @author: wangliying
 * @create: 2018-03-29 15:06
 **/
public class DynamicDataSourceContextHolder {
	/*
	 * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
	 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
	 */
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

	/**
	 * 使用setDataSourceType设置当前的
	 *
	 * @param dataSourceType
	 */
	public static void setDataSourceType(String dataSourceType) {

		logger.info("---DataSourceType set----" + dataSourceType.toLowerCase());
		contextHolder.set(dataSourceType.toLowerCase());
	}

	/**
	 * 获取数据源
	 *
	 * @return
	 */
	public static String getDataSourceType() {
		logger.info("---DataSourceType get----" + contextHolder.get());
		return contextHolder.get();
	}

	public static void clearDataSourceType() {
		logger.info("---DataSourceType remove----" + contextHolder.get());
		contextHolder.remove();
	}
}
