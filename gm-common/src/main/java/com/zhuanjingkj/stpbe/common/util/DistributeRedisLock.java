package com.zhuanjingkj.stpbe.common.util;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;


public class DistributeRedisLock {
    public static RedissonClient redissonClient;

    /**
     * 加锁成功....
     * @param lockName
     * @return
     */
    public static boolean lock(String lockName){
        try {
            if(null==redissonClient){  //如果对象没有注入进来那么说明是有问题的
                return false;
            }
            //获取这个锁
            RLock lock = redissonClient.getLock(lockName);
            //锁住了
            lock.lock(5, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 释放锁
     * @param lockName
     * @return
     */
    public static boolean unlock(String lockName){
        try {
            if(null==redissonClient){  //说明没法释放出问题了....
                return false;
            }
            //获取到这个锁对象
            RLock lock = redissonClient.getLock(lockName);
            if(null!=lock){
                lock.unlock();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}