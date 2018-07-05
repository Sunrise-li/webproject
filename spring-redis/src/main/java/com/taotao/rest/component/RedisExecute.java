package com.taotao.rest.component;

import redis.clients.jedis.Jedis;

public interface RedisExecute<E,T> {
	public T execute(E e);
}
