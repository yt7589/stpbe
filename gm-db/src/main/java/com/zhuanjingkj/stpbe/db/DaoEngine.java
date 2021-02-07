package com.zhuanjingkj.stpbe.db;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class DaoEngine<T extends Object> {
    private final static Logger logger = LoggerFactory.getLogger(DaoEngine.class);
    //@Autowired
    //private DataSourceRegistry dataSourceRegistry;
    private static List<String> globalDsOprns = null;

    public DaoEngine() {
        globalDsOprns = new ArrayList<>();
        // 定义需要使用全局数据库的方法
        globalDsOprns.add("***");
    }

    public List<T> query(String systemId, String opsName, String sql, Object[] params, RowMapper<T> rowMapper) {
        JdbcTemplate jt = getReadJdbcTemplate(systemId, opsName);
        return jt.query(sql, params, rowMapper);
    }

    private JdbcTemplate getReadJdbcTemplate(String systemId, String opsName) {
        return getJdbcTemplate(systemId, opsName, DataSourceRegistry.JDBC_TEMPLATE_MODE_READ);
    }

    private JdbcTemplate getWriteJdbcTemplate(String systemId, String opsName) {
        return getJdbcTemplate(systemId, opsName, DataSourceRegistry.JDBC_TEMPLATE_MODE_WRITE);
    }

    private JdbcTemplate getJdbcTemplate(String systemId, String opsName, String mode) {
        String type = "";
        if (globalDsOprns.contains(opsName)) {
            type = DataSourceRegistry.JDBC_TEMPLATE_TYPE_GLOBAL;
        } else {
            type = DataSourceRegistry.JDBC_TEMPLATE_TYPE_USER;
        }
        logger.info("##### login type=" + type + "!");
        return DataSourceRegistry.getInstance().getJdbcTemplate(type, systemId, mode);
    }
}
