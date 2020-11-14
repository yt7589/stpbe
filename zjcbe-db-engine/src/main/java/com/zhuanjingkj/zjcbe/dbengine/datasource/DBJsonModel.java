package com.zhuanjingkj.zjcbe.dbengine.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @description: 数据库Json文件
 * @author: wangliying
 * @create: 2018-03-30 12:41
 **/
public class DBJsonModel {
    @JsonProperty("DBusername")
    private String dbUsername;

    @JsonProperty("DBpw")
    private String dbPassword;

    @JsonProperty("DBname")
    private String dbName;

    @JsonProperty("DBaddr")
    private String dbAddress;

    @JsonProperty("DBport")
    private String dbPort;

    @JsonProperty("DBusertype")
    private String dbUserType;

    @JsonProperty("DBtype")
    private String dbType;

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(String dbAddress) {
        this.dbAddress = dbAddress;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbUserType() {
        return dbUserType;
    }

    public void setDbUserType(String dbUserType) {
        this.dbUserType = dbUserType;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    @Override
    public String toString() {
        return "DbInfo{" +
                "dbUsername='" + dbUsername + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", dbName='" + dbName + '\'' +
                ", dbAddress='" + dbAddress + '\'' +
                ", dbPort='" + dbPort + '\'' +
                ", dbUserType='" + dbUserType + '\'' +
                ", dbType='" + dbType + '\'' +
                '}';
    }
}
