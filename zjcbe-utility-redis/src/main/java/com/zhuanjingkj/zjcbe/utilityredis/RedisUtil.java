package com.zhuanjingkj.zjcbe.utilityredis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/8/19 15:36
 **/
@Component
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private RedisTemplate<String, Object> redisTemplate;

    private RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.setRedisTemplate(redisTemplate);
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //<editor-fold description="key操作">

    /**
     * 删除key
     */
    public void delete(String key) {

        try {

            this.redisTemplate.delete(key);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            throw ex;
        }

    }

    /**
     * 批量删除
     *
     * @param keys
     */
    public void delete(List<String> keys) {

        try {
            this.redisTemplate.delete(keys);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            throw ex;
        }

    }

    /**
     * 检查给定 key 是否存在
     */
    public Boolean hasKey(String key) {

        try {
            return this.redisTemplate.hasKey(key);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            throw ex;
        }

    }

    /**
     * 设置key的过期时间（单位：秒）
     */
    public Boolean expire(String key, int seconds) {

        try {
            return this.redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 设置key的过期时间
     */
    public Boolean expireAt(String key, Date date) {

        try {
            return this.redisTemplate.expireAt(key, date);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            return null;
        }

    }

    /**
     * 以秒为单位返回key的剩余有效时间
     */
    public Long getExpire(String key) {

        try {
            return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            return null;
        }

    }

    /**
     * 更换key名字
     */
    public void rename(String oldKey, String newKey) {

        try {
            this.redisTemplate.rename(oldKey, newKey);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
        }

    }

    //</editor-fold>

    //<editor-fold description="普通数据和String操作">

    /**
     * 将数据存入缓存
     */
    public void set(String key, Object val) {

        try {
            this.redisTemplate.opsForValue().set(key, val);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
        }

    }

    /**
     * 将数据存入缓存（并设置失效时间）
     */
    public void set(String key, Object val, long seconds) {
        try {
            this.redisTemplate.opsForValue().set(key, val, seconds, TimeUnit.SECONDS);
        } catch (Exception ex) {
            logger.error(String.format("redis读异常，异常信息：%s", ex.getMessage()));
            throw ex;
        }
    }

    /**
     * 批量获取
     *
     * @param keys
     * @return
     */
    public List<Object> get(List<String> keys) {
        try {
            List<Object> values = this.redisTemplate.opsForValue().multiGet(keys);
            return values;
        } catch (Exception ex) {
            logger.error(String.format("redis读异常，异常信息：%s", ex.getMessage()));
            throw ex;
        }
    }

    /**
     * 正则表达式获取所有的key
     */
   /* public Set<String> getKeys(String key) {
        try {
            Set<String> value = this.redisTemplate.keys(key);
            return value;
        } catch (Exception ex) {
            logger.error(String.format("哨兵:%s redis读异常，异常信息：%s", this.redisSentinelProperties.getNodes(), ex.getMessage()));
            throw ex;
        }
    }

    /**
     * 从缓存中获取数据
     */
    public String get(String key) {
        try {
            Object value = this.redisTemplate.opsForValue().get(key);
            return value == null ? null : value.toString();
        } catch (Exception ex) {
            logger.error(String.format("redis读异常，异常信息：%s", ex.getMessage()));
            throw ex;
        }
    }

    /**
     * 对象的获取
     */
    public <T> T getBean(String key, Class<T> clazz) {

        try {
            Object value = this.redisTemplate.opsForValue().get(key);
            if (value == null) {
                return null;
            }
            return JSON.parseObject(value.toString(), clazz);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            throw ex;
        }

    }

    /**
     * 集合对象的获取
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> getListBean(String key, Class<T> clazz) {
        try {
            Object value = this.redisTemplate.opsForValue().get(key);
            if (value == null) {
                return null;
            }
            return JSON.parseArray(value.toString(), clazz);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            throw ex;
        }

    }

    /**
     * 将自增变量存入缓存
     */
    public long increment(String key, long seqNo) {

        try {
            return this.redisTemplate.opsForValue().increment(key, seqNo);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            return 0L;
        }

    }

    /**
     * 将递增浮点数存入缓存
     */
    public void increment(String key, double val) {

        try {
            this.redisTemplate.opsForValue().increment(key, val);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
        }

    }

    /**
     * 将给定key的值设为value，并返回key的旧值。非字符串报错。
     */
    public String getAndSet(String key, double val) {

        try {
            return this.redisTemplate.opsForValue().getAndSet(key, val).toString();
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            return null;
        }

    }

    //</editor-fold>

    //<editor-fold description="队列List操作">

    /**
     * 移除并返回列表 key 的头元素
     */
    public Object leftPop(String key) {

        try {
            return this.redisTemplate.opsForList().leftPop(key);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            return null;
        }

    }

    /**
     * 将值插入到列表 key 的表头
     */
    public void leftPush(String key, Object val) {

        try {
            this.redisTemplate.opsForList().leftPush(key, val);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
        }

    }

    /**
     * 移除并返回列表 key 的尾元素
     */
    public Object rightPop(String key) {

        try {
            return this.redisTemplate.opsForList().rightPop(key);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            return null;
        }

    }

    public <T> T rightPop(String key, Class<T> clazz) {
        try {
            Object value = this.redisTemplate.opsForList().rightPop(key);
            if (value == null) {
                return null;
            }
            return JSON.parseObject(value.toString(), clazz);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * 将值插入到列表 key 的表尾
     */
    public void rightPush(String key, Object val) {

        try {
            this.redisTemplate.opsForList().rightPush(key, val);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
        }

    }

    /**
     * 返回列表 key 的长度
     */
    public Long size(String key) {

        try {
            return this.redisTemplate.opsForList().size(key);
        } catch (RuntimeException ex) {
            logger.error("Redis error:" + ex.getMessage(), ex);
            return null;
        }

    }

    //</editor-fold>
}