package com.Util;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public final class RedisUtil {

	/*
	 * The project uses RedisTemplate to operate Redis relative
	 */
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	public String getStr(String key) {
		return Strings.isBlank(key) ? null : stringRedisTemplate.boundValueOps(key).get();
	}

	/**
	 * the cache's expired time
	 * 
	 * @param key
	 * @param timeout
	 * @return
	 */
	public boolean expire(String key, long timeout) {
		try {
			if (timeout > 0) {
				redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}
	
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	@SuppressWarnings("unchecked")
	public void delCache(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}
	
	public Object get(String key) {
		return key ==null ? null : redisTemplate.opsForValue().get(key);
	}
	
	public boolean set(String key,Object value) {
		redisTemplate.opsForValue().set(key, value);
		return true;
	}
	
	public boolean set(String key, Object value, long time) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public long incr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("the delta must greater than 0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}
	// all api are essentially from Jedis,
	
	//adding ......
	//https://docs.spring.io/spring-data/redis/docs/2.1.6.RELEASE/reference/html/#redis
}
