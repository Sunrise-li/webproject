package com.taotao.rest.component;

public interface JedisClient {
	public String set(final String key,final String value);
	
	public String get(final String key);
	
	public Long hset(final String key,final String field,final String value);
	
	public String hget(final String key,final String field);
	
	public Long incr(final String key);
	
	public Long decr(final String key);
	
	public Long expire(final String key,final int second);
	
	public Long ttl(final String key);
	
	 public String set(final String key, final String value, final Integer seconds);
	 
	 public Long del(final String key);
	 
	 public Long hdel(final String key,final String field);
	 
	
}
