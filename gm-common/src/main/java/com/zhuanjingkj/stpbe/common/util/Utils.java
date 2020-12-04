package com.zhuanjingkj.stpbe.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDateTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
