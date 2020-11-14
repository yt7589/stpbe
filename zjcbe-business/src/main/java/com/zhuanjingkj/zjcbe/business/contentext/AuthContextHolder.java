package com.zhuanjingkj.zjcbe.business.contentext;

/**
 * @description:用户上下文
 * @author: liuxiaogang.bj
 * @create: 2019/8/7 14:40
 **/
public class AuthContextHolder {

    private static final ThreadLocal<AccountContextBO> contextHolder = new ThreadLocal<>();

    public static AccountContextBO getAuthContext() {
        return contextHolder.get();
    }

    public static void setAuthContext(AccountContextBO authContextBO) {
        contextHolder.set(authContextBO);
    }
}
