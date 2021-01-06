package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.DkHtfsDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkHtfsService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
public class DkHtfsService implements IDkHtfsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DkHtfsDTO getDkHtfsDTO_exp() {
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        DkHtfsDTO htfs = new DkHtfsDTO();
//        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//        System.out.println(date.substring(0,6));
//        ScanOptions options = ScanOptions.scanOptions().match("*" + "dk_htfs_" +  date.substring(0,6) + "*").count(31).build();
//        Cursor c = connection.scan(options);
//        Integer total = 0;
//        while(c.hasNext()) {
//            System.out.println(new String((byte[]) c.next()));
//        }
        Integer day = LocalDate.now().getDayOfMonth();
        Integer month = (int)(redisTemplate.opsForValue().get("dk_htfs_month") == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_month"));
        htfs.setTodayTf((int)(redisTemplate.opsForValue().get("dk_htfs_today")  == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_today")));
        htfs.setWeekTf((int)(redisTemplate.opsForValue().get("dk_htfs_week") == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_week"))); // 亿
        htfs.setMonthTf(month);
        htfs.setDailyTf(month/day);
        return htfs;
    }

}
