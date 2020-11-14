package com.zhuanjingkj.zjcbe.dbengine.datasource;

/**
 * @description: 数据库驱动
 * @author: wangliying
 * @create: 2018-03-30 13:46
 **/
public enum DBType {

    MS_SQL("MSSQL", "com.microsoft.sqlserver.jdbc.SQLServerDriver"),

    MY_SQL("MYSQL", "com.mysql.jdbc.Driver");

    private String key;

    private String driverClassName;

    DBType(String key, String driverClassName) {
        this.key = key;
        this.driverClassName = driverClassName;
    }

    public String getKey() {
        return this.key;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public static DBType getDbType(String key) {
        for (DBType dbTypeEnum : DBType.values()) {
            if (dbTypeEnum.getKey().equals(key.toUpperCase())) return dbTypeEnum;
        }

        throw new RuntimeException(String.format("未知DB类型:%s", key));
    }
}
