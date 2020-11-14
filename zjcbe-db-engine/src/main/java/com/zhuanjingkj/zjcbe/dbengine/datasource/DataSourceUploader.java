package com.zhuanjingkj.zjcbe.dbengine.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 数据源加载
 * @author: wangliying
 * @create: 2018-03-30 11:06
 **/
public class DataSourceUploader {

	/**
	 * 读权限标识
	 */
	private static final String READ_PREFIX = "R";
	/**
	 * 写权限标识
	 */
	private static final String WRITE_PREFIX = "W";

	/**
	 * 单库模板
	 */
	private static final String SINGLE_DATASOURCE_TEMPLATE = "%s_%s";

	private static Logger logger = LoggerFactory.getLogger(DataSourceUploader.class);

	/**
	 * @Description: 获取单库数据源
	 * @Param: [businesstype:数据库类型, write是否是写权限]
	 * @return: void
	 * @Author: wangliying
	 * @Date: 2018/3/30
	 */
	public static void switchSingleDataSource(DBBusinessType businesstype, DBVisitType visitType) {
		if (visitType == DBVisitType.SLAVE) {
			throw new RuntimeException("该库没有从库！");
		}
		String key = String.format(SINGLE_DATASOURCE_TEMPLATE, businesstype.getPrefix(), visitType == DBVisitType.WRITE ? WRITE_PREFIX : READ_PREFIX);
		logger.debug(String.format("切换数据源 - %s", key));
		DynamicDataSourceContextHolder.setDataSourceType(key);
	}
}
