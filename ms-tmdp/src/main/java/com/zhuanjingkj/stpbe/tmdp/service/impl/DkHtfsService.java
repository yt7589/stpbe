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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
public class DkHtfsService implements IDkHtfsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DkHtfsDTO getDkHtfsDTO_exp() {
        DkHtfsDTO htfs = new DkHtfsDTO();
        Integer day = LocalDate.now().getDayOfMonth();
        Integer total = (int)(redisTemplate.opsForValue().get("dk_htfs_month") == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_month"));
        Integer vtToday = (int)(redisTemplate.opsForValue().get("dk_htfs_today")  == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_today"));
        List<Integer> vWeek = redisTemplate.opsForList().range("dk_htfs_week", 0,6);
        Integer vtWeek = 0;
        for (Integer i : vWeek) {
            vtWeek += i;
        }
        htfs.setTodayTf(vtToday);
        htfs.setWeekTf(vtWeek + vtToday); // 亿
        htfs.setMonthTf(total);
        htfs.setDailyTf(total/day);
        return htfs;
    }

}
