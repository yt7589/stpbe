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
import java.util.Set;

@Component
public class DkHtfsService implements IDkHtfsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DkHtfsDTO getDkHtfsDTO_exp() {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        DkHtfsDTO htfs = new DkHtfsDTO();
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        ScanOptions options = ScanOptions.scanOptions().match("*" + "dk_htfs_" +  date.substring(0,6) + "*").count(31).build();
        Cursor c = connection.scan(options);
        Integer total = 0;
        while(c.hasNext()) {
            System.out.println(new String((byte[]) c.next()));
            total += (int)(redisTemplate.opsForValue().get(new String((byte[]) c.next()))== null ? 0 : redisTemplate.opsForValue().get(new String((byte[]) c.next())));
        }
        htfs.setTodayTf((int)(redisTemplate.opsForValue().get("dk_htfs_" + date)  == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_" + date)));
        htfs.setWeekTf((int)(redisTemplate.opsForValue().get("dk_htfs_" + date) == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_" + date))
                        + (int)(redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-1)) == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-1)))
                        + (int)(redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-2)) == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-2)))
                        + (int)(redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-3)) == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-3)))
                        + (int)(redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-4)) == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-4)))
                        + (int)(redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-5)) == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-5)))
                        + (int)(redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-6)) == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_" + DateUtil.plusDays(-6)))); // 亿
        htfs.setMonthTf(total);
        htfs.setDailyTf(total/30);
        return htfs;
    }

}
