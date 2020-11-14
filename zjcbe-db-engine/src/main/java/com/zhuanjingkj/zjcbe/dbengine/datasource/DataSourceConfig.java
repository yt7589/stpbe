package com.zhuanjingkj.zjcbe.dbengine.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuanjingkj.zjcbe.dbengine.transcation.MultiDataSourceTransactionFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @description: 数据源配置
 * @author: wangliying
 * @create: 2018-03-30 11:06
 **/
@Configuration
public class DataSourceConfig {

    /**
     * SqlServer连接串模板
     */
    private final static String MS_SQL_TEMPLATE = "jdbc:sqlserver://%s:%s;DatabaseName=%s";

    /**
     * SqlServer连接串模板
     */
    private final static String MY_SQL_TEMPLATE = "jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=false";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${mybatis.mapper-locations}")
    private String MAPPER_LOCATION;

    /**
     * DB配置文件路径
     */
    @Value("${db.config.path}")
    private String dbConfigPath;

    /**
     * @Description: 获取数据源字典
     * @Param: []
     * @return: javax.sql.DataSource
     * @Author: wangliying
     * @Date: 2018/3/30
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dataSource() throws Exception {
        Map<String, DBJsonModel> dbInfos = this.loadConfigFile();
        if (dbInfos == null) {
            return null;
        }

        AbstractRoutingDataSource resolver = new DynamicRoutingDataSource();

        Map<Object, Object> dataSources = new HashMap<Object, Object>();

        Boolean isSetDefaultDataSource = false;

        for (Map.Entry<String, DBJsonModel> entry : dbInfos.entrySet()) {
            System.out.println("yt:" + entry.getKey() + ": " + entry.getValue() + "!");
            DataSource ds = this.buildDruidDataSource(entry.getValue());
            dataSources.put(entry.getKey().toLowerCase(), ds);
            if (!isSetDefaultDataSource) {
                resolver.setDefaultTargetDataSource(ds);
                isSetDefaultDataSource = true;
            }
        }
        resolver.setTargetDataSources(dataSources);
        return resolver;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        logger.info("--------------------  sqlSessionFactory init ---------------------");
        try {
            MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dynamicDataSource);
            sessionFactoryBean.setTransactionFactory(new MultiDataSourceTransactionFactory());
            //设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION);
            System.out.println("####################### load mapper xml");
            for (int i=0; i<resources.length; i++) {
                System.out.println("***************** m_" + i + ": " + resources[i].getFilename() + "!");
            }
            sessionFactoryBean.setMapperLocations(resources);
            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.error("mybatis resolver mapper*xml is error", e);
            return null;
        } catch (Exception e) {
            logger.error("mybatis sqlSessionFactoryBean create error", e);
            return null;
        }
    }

    /**
     * @Description: 加载json格式配置文件
     * @Param: []
     * @return: java.util.Map<java.lang.String DbInfo>
     * @Author: wangliying
     * @Date: 2018/3/30
     */
    public Map<String, DBJsonModel> loadConfigFile() {
        this.logger.info(String.format("加载DB配置文件：%s", dbConfigPath));

        String content = null;
        try {
            //文件系统中需要使用File加载,classPath需要使用ClassLoader加载(本地使用ClassLoader加载)
            if (StringUtils.startsWithIgnoreCase(dbConfigPath, "/")) {
                System.out.println("yt: 1 " + dbConfigPath);
                content = new Scanner(new File(dbConfigPath)).useDelimiter("\\Z").next();
                System.out.println("yt:  1 content=" + content + "!");
            } else {
                System.out.println("yt: 2 " + dbConfigPath);
                InputStream inputStream = DataSourceConfig.class.getClassLoader().getResourceAsStream(dbConfigPath);
                content = new Scanner(inputStream).useDelimiter("\\Z").next();//以“，”分割读取内容
                System.out.println("yt:  2 content=" + content + "!");
            }
        } catch (FileNotFoundException e) {
            this.logger.error(String.format("加载DB配置文件失败：%s", dbConfigPath), e);
            return null;
        } catch (RuntimeException e) {
            this.logger.error(String.format("加载DB配置文件失败：%s", dbConfigPath), e);
            return null;
        }

        Map<String, DBJsonModel> map;
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(content, new TypeReference<HashMap<String, DBJsonModel>>() {
            });
            return map;
        } catch (Exception e) {
            this.logger.error(String.format("序列化DB配置文件失败：%s", dbConfigPath), e);
            return null;
        }
    }

    /**
     * @Description: 根据DB配置信息初始化数据源
     * @Param: [dbInfo:数据库连接配置]
     * @return: javax.sql.DataSource
     * @Author: wangliying
     * @Date: 2018/3/30
     */
    private DataSource buildDruidDataSource(DBJsonModel dbInfo) throws Exception {

        Map<Object, Object> properties = new HashMap<Object, Object>();

        String url = "";
        switch (DBType.getDbType(dbInfo.getDbType())) {
            case MS_SQL:
                url = String.format(MS_SQL_TEMPLATE, dbInfo.getDbAddress(), dbInfo.getDbPort(), dbInfo.getDbName());
                break;
            case MY_SQL:
                System.out.println("############# addr: " + dbInfo.getDbAddress() + "!");
                url = String.format(MY_SQL_TEMPLATE, dbInfo.getDbAddress(), dbInfo.getDbPort(), dbInfo.getDbName());
                break;
            default:
                this.logger.error(String.format("DB初始化失败，类型未知：%s", dbInfo.toString()));
                throw new RuntimeException();
        }
        properties.put("url", url);
        properties.put("username", dbInfo.getDbUsername());
        properties.put("password", dbInfo.getDbPassword());
        properties.put("maxActive", "100");
        properties.put("maxWait", "5000");
        properties.put("testOnBorrow", "false");
        properties.put("testOnReturn", "false");
        properties.put("testWhileIdle", "true");
        properties.put("validationQuery", "select 1");
        properties.put("initialSize", "1");
        properties.put("filters", "stat");
        properties.put("connectionProperties", "druid.stat.slowSqlMillis=500");
        properties.put("useGlobalDataSourceStat", "true");
        properties.put("timeBetweenEvictionRunsMillis", "60000");
        properties.put("poolPreparedStatements", "true");
        properties.put("minEvictableIdleTimeMillis", "300000");
        return DruidDataSourceFactory.createDataSource(properties);
    }
}
