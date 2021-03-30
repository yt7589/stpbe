package com.zhuanjingkj.stpbe.db;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceRegistry {
    private final static Logger logger = LoggerFactory.getLogger(DataSourceRegistry.class);
    public final static String JDBC_TEMPLATE_TYPE_USER = "u";
    public final static String JDBC_TEMPLATE_TYPE_GLOBAL = "g";
    public final static String JDBC_TEMPLATE_MODE_READ = "r";
    public final static String JDBC_TEMPLATE_MODE_WRITE = "w";
    private static Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>();
    private static DataSourceRegistry instance = null;

    public static DataSourceRegistry getInstance() {
        Object obj = AppRegistry.getParam(PropUtil.getValue("APP_CTX")).orElse(Thread.currentThread());
        if (instance == null) {
            synchronized (obj) {
                instance = new DataSourceRegistry();
            }
        }
        return instance;
    }

    private DataSourceRegistry() {
        DataSource ds = null;
        String dbUrl = "jdbc:mysql://192.168.2.68:3306/StpDb?useSSL=false";
        String dbUser = "stp";
        String dbPwd = "Stp2020";
        long systemId = 1;
        ds = DataSourceFactory.createDataSource(DataSourceFactory.DB_MYSQL, dbUrl, dbUser, dbPwd);
        JdbcTemplate jt = new JdbcTemplate(ds);
        String key = null;
        // 全局只读键
        key = JDBC_TEMPLATE_TYPE_GLOBAL + "_" + JDBC_TEMPLATE_MODE_READ;
        jdbcTemplates.put(key, jt);
        // 全局可写键
        key = JDBC_TEMPLATE_TYPE_GLOBAL + "_" + JDBC_TEMPLATE_MODE_WRITE;
        jdbcTemplates.put(key, jt);
        // 用户只读键
        key = JDBC_TEMPLATE_TYPE_USER + "_" + systemId + "_" + JDBC_TEMPLATE_MODE_READ;
        jdbcTemplates.put(key, jt);
        // 用户可写键
        key = JDBC_TEMPLATE_TYPE_USER + "_" + systemId + "_" + JDBC_TEMPLATE_MODE_WRITE;
        jdbcTemplates.put(key, jt);
    }

    public JdbcTemplate getJdbcTemplate(String type, String systemId, String mode) {
        String key = "";
        if (type.equals("u")) {
            key = type + "_" + systemId + "_" + mode;
        } else {
            key = type + "_" + mode;
        }
        logger.info("###### jt key=" + key + "!");
        return jdbcTemplates.get(key);
    }
}
