package com.taotao.rest.component.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.rest.component.JedisClient;
import com.taotao.rest.component.RedisExecute;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.RedisInputStream;

public class JedisClientSingle implements JedisClient {
	
	@Autowired(required = false)
	private JedisPool pool;
	
	private <T> T execute(RedisExecute<Jedis, T> redisExecute) {
		Jedis jedis = null;
		try {
			//从连接池中获取到jedis对象
			jedis = pool.getResource();
			return redisExecute.execute(jedis);
		}catch (Exception e) {
			e.printStackTrace();;
		}finally {
			if(null != jedis) {
				 // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 保存数据到redis中
	 */
	@Override
	public String set(String key, String value) {
		return this.execute(new RedisExecute<Jedis, String>() {
			@Override
			public String execute(Jedis e) {
				return e.set(key, value);
			}
		});
	}

	/**
	 * 通过key查询数据
	 */
	@Override
	public String get(String key) {
		return this.execute(new RedisExecute<Jedis, String>() {
			@Override
			public String execute(Jedis e) {
				return e.get(key);
			}
		});
	}

	/**
	 * 保存Hash数据到redis
	 */
	@Override
	public Long hset(String key, String field, String value) {
		return this.execute(new RedisExecute<Jedis, Long>() {
			@Override
			public Long execute(Jedis e) {
				return e.hset(key, field, value);
			}
		});
	}

	/**
	 * 获得hash值
	 */
	@Override
	public String hget(String key, String field) {
		return this.execute(new RedisExecute<Jedis, String>() {
			@Override
			public String execute(Jedis e) {
				return e.hget(key, field);
			}
		});
	}
	
/**
 * 递增
 */
	@Override
	public Long incr(String key) {
		return this.execute(new RedisExecute<Jedis, Long>() {
			@Override
			public Long execute(Jedis e) {
				return e.incr(key);
			}
		});
	}

	/**
	 * 递减
	 */
	@Override
	public Long decr(String key) {
		return this.execute(new RedisExecute<Jedis, Long>() {
			@Override
			public Long execute(Jedis e) {
				return e.decr(key);
			}
		});
	}

	@Override
	public Long expire(String key, int second) {
		return this.execute(new RedisExecute<Jedis, Long>() {
			@Override
			public Long execute(Jedis e) {
				return e.expire(key, second);
			}
		});
	}
	/**
	 * 查看数据的生存时间
	 */
	@Override
	public Long ttl(String key) {
		return this.execute(new RedisExecute<Jedis, Long>() {
			@Override
			public Long execute(Jedis e) {
				return e.ttl(key);
			}
		});
	}

	@Override
	public String set(String key, String value, Integer seconds) {
		return null;
	}

	/**
	 * 删除数据
	 */
	@Override
	public Long del(String key) {
		return this.execute(new RedisExecute<Jedis, Long>() {
			@Override
			public Long execute(Jedis e) {
				return e.del(key);
			}
		});
	}

	@Override
	public Long hdel(String key, String field) {
		return this.execute(new RedisExecute<Jedis, Long>() {
			@Override
			public Long execute(Jedis e) {
				return e.hdel(key, field);
			}
		});
	}
}
