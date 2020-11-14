package com.zhuanjingkj.zjcbe.utilityredis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/8/19 15:32
 **/
@Configuration
public class RedisConfig {
	@Value("${redis.pool.maxIdle}")
	private Integer maxIdle;

	@Value("${redis.pool.minIdle}")
	private Integer minIdle;

	@Value("${redis.pool.maxTotal}")
	private Integer maxTotal;

	@Value("${redis.pool.maxWaitMillis}")
	private Integer maxWaitMillis;

	@Value("${redis.default.hostName}")
	private String hostName;

	@Value("${redis.default.password}")
	private String password;

	@Value("${redis.default.port}")
	private Integer port;

	/**
	 * JedisPoolConfig 连接池
	 *
	 * @return
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(maxIdle);
		//最小空闲连接数
		jedisPoolConfig.setMinIdle(minIdle);
		// 连接池的最大数据库连接数
		jedisPoolConfig.setMaxTotal(maxTotal);
		// 最大建立连接等待时间
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

		return jedisPoolConfig;
	}

	/**
	 * 单机版配置
	 *
	 * @param @param  jedisPoolConfig
	 * @param @return
	 * @return JedisConnectionFactory
	 * @throws
	 * @Title: JedisConnectionFactory
	 */
	@Bean
	public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(hostName);
		redisStandaloneConfiguration.setPort(port);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().poolConfig(jedisPoolConfig).build();
		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
	}

	/**
	 * 实例化 RedisTemplate 对象
	 *
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		initRedisTemplate(redisTemplate, redisConnectionFactory);
		return redisTemplate;
	}

	/**
	 * 设置数据存入 redis 的序列化方式,并开启事务
	 *
	 * @param redisTemplate
	 * @param factory
	 */
	private void initRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
		//如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
		// 开启事务
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setConnectionFactory(factory);
	}

}
