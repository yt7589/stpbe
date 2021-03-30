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

    /** 从配置文件中，获取单个属性值，分为两步进行，第一步从application.properties文件中取出profiles.active属性，
     * 找到当前的配置文件，然后从当前配置文件中读出指定的属性值
     */
    public static String getValue(String name) {
        String profilesActive = getPropertyValue("application.properties", "spring.profiles.active");
        String value = getPropertyValue("application-" + profilesActive + ".properties", name);
        log.info("############ value=" + value + "!!!!!!!!!!!!!!!!!!");
        return value;
    }

    /**
     * 从当前活跃的配置文件中读出指定的属性值
     * @param propertiesFile 当前活跃的配置文件，由application.properties中的spring.profiles.active指定
     * @param key 属性名称
     * @return 属性值，如果未找到则返回空
     */
    private static String getPropertyValue(String propertiesFile, String key) {
        System.out.println("########## 获取属性值：" + key + "!");
        Properties p = new Properties();
        InputStream inStream = PropUtil.class.getClassLoader().getResourceAsStream(propertiesFile);
        InputStream consts = PropUtil.class.getClassLoader().getResourceAsStream("AppConst.properties");
        String value = "";
        try {
            p.load(inStream);
            p.load(consts);
            value = p.getProperty(key);
        } catch (Exception e) {
            log.error("读取属性文件错误:",e);
        }
        return value;
    }

    /**
     * 设置本地号牌号码前缀，如：京A-XY123。京字即为号牌号码前缀
     */
    @PostConstruct
    public void  init() {
        HPHM_PRE = getValue("hphm.native.prefix");
    }

    public static String getHphmPre() {
        return HPHM_PRE;
    }
}
