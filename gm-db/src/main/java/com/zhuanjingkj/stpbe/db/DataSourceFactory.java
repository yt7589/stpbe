package com.zhuanjingkj.stpbe.db;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    public final static String DB_MYSQL = "mysql";

    public static DataSource createDataSource(String dbName, String dbUrl, String dbUser, String dbPwd) {
        if (dbName.equals(DB_MYSQL)) {
            return createMysqlDataSource(dbUrl, dbUser, dbPwd);
        }
        return null;
    }

    private static MysqlDataSource createMysqlDataSource(String dbUrl, String dbUser, String dbPwd) {
        MysqlDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setURL(dbUrl);
        ds.setUser(dbUser);
        ds.setPassword(dbPwd);
        return ds;
    }
}
