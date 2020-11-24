package com.zhuanjingkj.stpbe.tmdp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author by guoqiang
 * date on 2020.11.23
 **/
public class CommentUtils {

    /**
     * 生成一天二十四小时中的偶数时间
     * @return
     */
    public static Map<Integer,String> generateDayTime(Date date) {

        Date today = date;
        if(date == null){
            today = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<Integer, String> map = new HashMap<>();
        String todayStr = sdf.format(today);
        String zero = todayStr + " 00:00:00";
        map.put(0, zero);
        String two = todayStr + " 02:00:00";
        map.put(1, two);
        String four = todayStr + " 04:00:00";
        map.put(2, four);
        String six = todayStr + " 06:00:00";
        map.put(3, six);
        String eight = todayStr + " 08:00:00";
        map.put(4, eight);
        String ten = todayStr + " 10:00:00";
        map.put(5, ten);
        String twelve = todayStr + " 12:00:00";
        map.put(6, twelve);
        String fourth = todayStr + " 14:00:00";
        map.put(7, fourth);
        String sixteen = todayStr + " 16:00:00";
        map.put(8, sixteen);
        String eighteen = todayStr + " 18:00:00";
        map.put(9, eighteen);
        String twenty = todayStr + " 20:00:00";
        map.put(10, twenty);
        String twentyTwo = todayStr + " 22:00:00";
        map.put(11, twentyTwo);
        String twentyFour = todayStr + " 23:59:59";
        map.put(12, twentyFour);
        return map;
    }
}
