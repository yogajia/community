package com.example.accountbook.Utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

public class RedisUtils {
    public static void hashput(String key,String hkey,Object value){
        getRedistemplate().opsForHash().put(key,hkey,value);
    }

    public static Object hashget(String key,String hkey){
        return getRedistemplate().opsForHash().get(key,hkey);
    }

    public static void Stringput(String key,Object value){
        getRedistemplate().opsForValue().set(key,value);
    }
    public static Object getStringvalue(String key){
        return getRedistemplate().opsForValue().get(key);
    }

    public static void setExpire(String key,long time){
        getRedistemplate().expire(key,time, TimeUnit.SECONDS);
    }
    public static RedisTemplate getRedistemplate(){
        RedisTemplate redisTemplate=(RedisTemplate) AppContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
