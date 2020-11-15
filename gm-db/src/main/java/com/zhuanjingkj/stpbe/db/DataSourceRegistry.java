package com.zhuanjingkj.stpbe.db;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceRegistry {
    public final static String JDBC_TEMPLATE_TYPE_USER = "u";
    public final static String JDBC_TEMPLATE_TYPE_GLOBAL = "g";
    public final static String JDBC_TEMPLATE_MODE_READ = "r";
    public final static String JDBC_TEMPLATE_MODE_WRITE = "w";
    private static Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>();

    public DataSourceRegistry() {
        DataSource ds = null;
        String dbUrl = "jdbc:mysql://192.168.2.68:3306/vehicle";
        String dbUser = "root";
        String dbPwd = "Zhang.1986";
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

    public JdbcTemplate getJdbcTemplate(String type, long systemId, String mode) {
        String key = "";
        if (type.equals("u")) {
            key = type + "_" + systemId + "_" + mode;
        } else {
            key = type + "_" + mode;
        }
        return jdbcTemplates.get(key);
    }
}
