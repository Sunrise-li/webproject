package com.taotao.rest.component.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.rest.component.JedisClient;

import redis.clients.jedis.JedisCluster;

/**
 * Redis集群
 * @author Sunrise
 *
 */
public class JedisClientCluster implements JedisClient{

	@Autowired
	private JedisCluster cluster;
	@Override
	public String set(String key, String value) {
		return this.cluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return this.cluster.get(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return this.cluster.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		return this.hget(key, field);
	}

	@Override
	public Long incr(String key) {
		return this.incr(key);
	}

	@Override
	public Long decr(String key) {
		return this.cluster.decr(key);
	}

	@Override
	public Long expire(String key, int second) {
		return this.cluster.expire(key, second);
	}

	@Override
	public Long ttl(String key) {
		return this.cluster.ttl(key);
	}

	@Override
	public String set(String key, String value, Integer seconds) {
		return null;
	}
	
	@Override
	public Long del(String key) {
		return this.cluster.decr(key);
	}

}
