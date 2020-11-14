package com.zhuanjingkj.zjcbe.utility.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置类基类
 * 用于从apollo获取的配置，并应用在单例对象中。用以标记配置变化，从而确定是否重新生成对象
 */
public abstract class ChangeScope {

	/**
	 * 是否变化
	 */
	private boolean isChange;

	/**
	 * 已经变化的对象类名
	 */
	private List<String> classNames;

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean change) {
		isChange = change;
	}

	public List<String> getClassNames() {
		return classNames;
	}

	public void addClassNames(String className) {
		this.classNames.add(className);
	}

	public void initClassNames() {
		this.classNames = new ArrayList<>();
	}
}
