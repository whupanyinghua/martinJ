package com.pyh.dlock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 类MDLock的实现描述：基于redis、lua脚本的分布式锁
 *
 * @author panyinghua 2020-7-31 12:16
 */
public class MDLock {

    private final static long DEFAULT_TIME_OUT = 10;
    private final static TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    // 如果是依赖于spring，则使用spring bean注入redisTemplate对象
    RedisTemplate<String, String> redisTemplate;


    public boolean lock(String key, String value, long timeOut, TimeUnit timeUnit) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, timeOut>=0?timeOut:DEFAULT_TIME_OUT, null != timeUnit?timeUnit:DEFAULT_TIME_UNIT);
        return null!=result?result:false;
    }

    public void unlock(String key, String value) {
        redisTemplate.execute(DEL_LUA_SCRIPT, Collections.singletonList(key), Collections.singletonList(value));
    }


    // 根据传入的key、value，进行redis删除操作的lua脚本
    private final static String DEL_REDIS_KEY_WITH_VALUE_LUA = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    // 直接静态化redisscript脚本对象，因为该对象在整个MDLock中是不变的，因此直接声明为final static类型
    private final static RedisScript<Integer> DEL_LUA_SCRIPT = RedisScript.of(DEL_REDIS_KEY_WITH_VALUE_LUA);
}
