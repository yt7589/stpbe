package com.zhuanjingkj.zjcbe.dbengine.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description: 动态切换数据源
 * AbstractRoutingDataSource充当了DataSource的路由中介, 能有在运行时, 根据某种key值来动态切换到真正的DataSource上
 * @author: wangliying
 * @create: 2018-03-29 11:06
 **/
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private static Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class);
     /**
     * @Description: 代码中的determineCurrentLookupKey方法取得一个字符串
     * @Param: []
     * @return: java.lang.Object
     * @Author: wangliying
     * @Date: 2018/3/30
     */
    @Override
    protected Object determineCurrentLookupKey() {

        logger.debug("调用---------determineCurrentLookupKey----------动态数据源为："+DynamicDataSourceContextHolder.getDataSourceType());
        /*
         * DynamicDataSourceContextHolder代码中使用setDataSourceType
         * 设置当前的数据源，在路由类中使用getDataSourceType进行获取，
         *  交给AbstractRoutingDataSource进行注入使用。
         */
        return DynamicDataSourceContextHolder.getDataSourceType();


    }

}
