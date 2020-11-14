package com.zhuanjingkj.zjcbe.utility.guid;

import java.util.UUID;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/9/29 17:43
 **/
public class GuidUtil {

	public static String newId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
