package com.zhuanjingkj.stpbe.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Properties;

@Component
public class PropUtil {

    private static final Logger log = LoggerFactory.getLogger(PropUtil.class);

    public static String HPHM_PRE = null;

    /** 从配置文件中，获取单个属性值*/
    public static String getValue(String name) {
        String profilesActive = getPropertyValue("application.properties", "spring.profiles.active");
        String value = getPropertyValue("application-" + profilesActive + ".properties", name);
        log.info("############ value=" + value + "!!!!!!!!!!!!!!!!!!");
        return value;
    }

    private static String getPropertyValue(String propertiesFile, String key) {
        Properties p = new Properties();
        InputStream inStream = PropUtil.class.getClassLoader().getResourceAsStream(propertiesFile);
        String value = "";
        try {
            p.load(inStream);
            value = p.getProperty(key);
        } catch (Exception e) {
            log.error("读取属性文件错误:",e);
        }
        return value;
    }

    @PostConstruct
    public void  init() {
        HPHM_PRE = getValue("hphm.native.prefix");
    }

    public static String getHphmPre() {
        return HPHM_PRE;
    }
}
