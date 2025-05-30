package com.social.redis;

import jakarta.annotation.Resource;
import org.redisson.api.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service("redissonService")
public class RedissonService implements IRedisService {
    @Resource
    private RedissonClient redissonClient;

    public <T> void setValue(String key, T value) {
        redissonClient.<T>getBucket(key).set(value);
    }

    @Override
    public <T> void setValue(String key, T value, long expired) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value, Duration.ofMillis(expired));
    }

    public <T> T getValue(String key) {
        return redissonClient.<T>getBucket(key).get();
    }

    @Override
    public void remove(String key) {
        redissonClient.getBucket(key).delete();
    }
}
