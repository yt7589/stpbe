package com.zhuanjingkj.stpbe.common;

public class AppConst {
    public final static String JWT_KEY = "zjc";
    // 认证相关
    public final static String AUTH_REDIS_USER_PREFIX = "u_";
    public final static long REDIS_USER_DURATION = 60 * 1000;
    public final static String AUTH_USER_HEADER = "User-Id";
    public final static String APP_CTX = "appCtx";
    // Kafka相关
    public final static String KAFKA_GROUP_JSON_RAW = "raw";
    public final static String KAFKA_GROUP_KB_TITF = "kbTitf";
}
