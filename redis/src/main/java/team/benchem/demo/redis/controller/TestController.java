package team.benchem.demo.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.benchem.demo.redis.service.RedisClient;

@RequestMapping("/")
@RestController
public class TestController {

    @Autowired
    RedisClient redisClient;

    @RequestMapping("/set")
    public String setValue(
            @RequestParam String value,
            @RequestParam @Nullable Integer timeout
    ){
        if(timeout != null){
            return redisClient.setValue(value, timeout);
        } else {
            return redisClient.setValue(value);
        }
    }

    public void updateExpireTime(
            @RequestParam String key,
            @RequestParam Integer timeout
    ){
        redisClient.updateExpireTime(key, timeout);
    }

    @RequestMapping("/get")
    public String getValue(@RequestParam String key){
        return redisClient.getValue(key);
    }

}
