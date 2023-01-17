package com.example.accountbook.Cache;

import com.example.accountbook.Utils.AppContextUtils;
import com.example.accountbook.Utils.RedisUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class MybaitsRedisCache implements Cache {

    private final String id;

    public MybaitsRedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate=getRedisTemplate();
        redisTemplate.opsForHash().put(id,key.toString(),value);
        RedisUtils.setExpire(id,86400);
    }

    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate=getRedisTemplate();
        return redisTemplate.opsForHash().get(id,key.toString());
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate=getRedisTemplate();
        redisTemplate.delete(id);
    }

    @Override
    public int getSize() {
        RedisTemplate redisTemplate=getRedisTemplate();
        return redisTemplate.opsForHash().size(id).intValue();
    }

    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate= (RedisTemplate) AppContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
