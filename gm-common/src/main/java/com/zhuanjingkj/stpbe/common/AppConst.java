package com.zhuanjingkj.stpbe.common;

public class AppConst {
    public final static String JWT_KEY = "zjc";
    // 认证相关
    public final static String AUTH_REDIS_USER_PREFIX = "u_";
    public final static long REDIS_USER_DURATION = 60 * 1000;
    public final static String AUTH_USER_HEADER = "User-Id";
    public final static String APP_CTX = "appCtx";
    // 视频识别
    public final static String VIDEO_RECOG_RST_REDIS_KEY = "rtsp_result";
    // Kafka相关
    public final static String KAFKA_GROUP_JSON_RAW = "raw";
    public final static String KAFKA_GROUP_KB_TITF = "kbTitf";
    public final static String KAFKA_SERVER = "192.168.2.68:9092";
    // TVIS相关
    public final static String TVIS_SERVER_URL = "http://192.168.2.68:6666/vehicle/function/recognition";
    public final static String VIDEO_TVIS_ADDR = "192.168.2.68";
    public final static short VIDEO_TVIS_PORT = 3030;
    // Milvus图搜相关
    public final static String MILVUS_SERVER_ADDR = "192.168.2.15";
    public final static short MILVUS_SERVER_PORT = 19530;
    // IPFS相关
    public final static String IPFS_API_URL = "http://192.168.2.68:5005/api/v0/"; // 加命令 add, cat等
    public final static String IPFS_GW_URL = "http://192.168.2.68.9095/ipfs/"; // 加文件hash
}
