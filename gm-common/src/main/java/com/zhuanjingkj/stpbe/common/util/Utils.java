package com.zhuanjingkj.stpbe.common.util;

import java.util.UUID;

/**
 * author by guoqiang
 * date on 2020.12.03
 **/
public class Utils {

    /**
     * 生成uuid
     * @return
     */
    public static String UUID(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
}
