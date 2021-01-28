package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 首页区县数据
 */
@Component
public class DkDctfObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000001", 1);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000002", 2);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000003", 3);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000004", 4);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000005", 5);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000006", 6);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000007", 7);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000008", 8);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000009", 9);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000010", 10);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000011", 11);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000012", 12);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000013", 13);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000014", 14);
        redisTemplate.opsForHash().increment("dk_dctf_area", "C0000015", 10);
    }

    @Override
    public void initialize(Environment env) {

    }
}
