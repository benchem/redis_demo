package team.benchem.demo.redis.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisClient {

    @Autowired
    StringRedisTemplate redisTemplate;

    public String setValue(String str){
        String key = String.format("token:%s", UUID.randomUUID());
        redisTemplate.opsForValue().set(key, str);
        return key;
    }

    public String setValue(String str, Integer expireSeconds){
        String key = String.format("token:%s", UUID.randomUUID());
        redisTemplate.opsForValue().set(key, str);
        updateExpireTime(key, expireSeconds);
        return key;
    }

    public void updateExpireTime(String key, Integer expireSeconds){
        redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
    }

    public String getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

}
