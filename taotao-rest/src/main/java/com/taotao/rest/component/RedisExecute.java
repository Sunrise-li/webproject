package com.taotao.rest.component;

public interface RedisExecute<E,T> {
	public T execute(E e);
}
