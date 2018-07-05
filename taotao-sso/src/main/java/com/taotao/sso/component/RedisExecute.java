package com.taotao.sso.component;

public interface RedisExecute<E,T> {
	public T execute(E e);
}
