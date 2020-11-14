package com.zhuanjingkj.zjcbe.utility.map;

import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 对象映射工具类
 * @author: zhiwe
 * @create: 2018-06-13 10:58
 **/
public class ObjectMapperUtils {

    private static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();
    private static Object syncObj = new Object();

    /**
     * 对象映射（BeanCopier）
     *
     * @param source
     * @param target
     */
    public static void map(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!beanCopierMap.containsKey(beanKey)) {
            synchronized (syncObj) {
                if (!beanCopierMap.containsKey(beanKey)) {
                    copier = BeanCopier.create(source.getClass(), target.getClass(), false);
                    beanCopierMap.put(beanKey, copier);
                } else {
                    copier = beanCopierMap.get(beanKey);
                }
            }
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    private static String generateKey(Class<?> classSource, Class<?> classTarget) {
        return classSource.toString() + classTarget.toString();
    }
}
