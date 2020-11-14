package com.zhuanjingkj.zjcbe.utility.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampUtil {
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = "";
        if (!"".equals(s)) {
            try {
                res = String.valueOf(sdf.parse(s).getTime() / 1000);
            } catch (Exception e) {
                System.out.println("传入了null值");
            }
        } else {
            long time = System.currentTimeMillis();
            res = String.valueOf(time / 1000);
        }
        return res;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(Date date) {
        return String.valueOf(date.getTime() / 1000);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(int time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(new Date(time * 1000L));
        return times;
    }
}
