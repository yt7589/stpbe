package com.zhuanjingkj.zjcbe.applications.messagejob;

import com.zhuanjingkj.zjcbe.commondata.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/8/28 14:48
 **/

@Configuration
public class RedisMessageConfig {

    @Autowired
    VideoMessageSubscriber redisMessageSubscriber;

    @Autowired
    CommonConfig autoConfig;

    @Bean
    RedisMessageListenerContainer redisContainer(@Qualifier("JedisConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(redisMessageSubscriber, getVideoTopic());
        return container;
    }

    @Bean("videoTopic")
    public ChannelTopic getVideoTopic() {
        return new ChannelTopic(autoConfig.getVideoQueenName());
    }
}
