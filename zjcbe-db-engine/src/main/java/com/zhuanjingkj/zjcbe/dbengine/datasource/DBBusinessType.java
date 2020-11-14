package com.zhuanjingkj.zjcbe.dbengine.datasource;

/**
 * @description: 数据源类型
 * @author: wangliying
 * @create: 2018-03-30
 **/
public enum DBBusinessType {

	/**
	 * MYSQL测试库
	 */
	BUS_LINE("linebus");

	private String prefix;

	DBBusinessType(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}

}
