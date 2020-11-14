package com.zhuanjingkj.zjcbe.business.redismessage;

import com.zhuanjingkj.zjcbe.commondata.config.CommonConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class VideoMessagePublisher {

	private RedisTemplate<String, Object> redisTemplate;

	private ChannelTopic topic;

	VideoMessagePublisher(RedisTemplate<String, Object> redisTemplate, CommonConfig autoConfig) {
		this.redisTemplate = redisTemplate;
		this.topic = new ChannelTopic(autoConfig.getVideoQueenName());
	}

	public void publish(String message) {
		redisTemplate.convertAndSend(topic.getTopic(), message);
	}

	public void publish(Object obj) {
		redisTemplate.convertAndSend(topic.getTopic(), obj);
	}
}
