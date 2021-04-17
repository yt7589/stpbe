package com.zhuanjingkj.stpbe.tvis_server.conf;

import com.zhuanjingkj.stpbe.common.util.PropUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    public final static Logger logger = LoggerFactory.getLogger(RedissonConfig.class);
    @Bean
    public RedissonClient redissonClient() {
        RedissonClient redissonClient = null;
        //获取config的实例
        Config config = new Config();
        //设置请求的URL地址
        String url = "redis://" + PropUtil.getValue("spring.redis.host") +
                ":" + PropUtil.getValue("spring.redis.port");
        //设置config
        config.useSingleServer().setAddress(url);
        //通过Redisson来创建一个客户端对象
        try {
            redissonClient = Redisson.create(config);
            logger.info("创建RedissonClient成功");
            return redissonClient;
        } catch (Exception err) {
            logger.info("创建RedissonClient失败:" + err.fillInStackTrace());
            return null;
        }

    }
}
