package com.taotao.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.rest.component.JedisClient;
import com.taotao.rest.component.impl.JedisClientCluster;
import com.taotao.rest.component.impl.JedisClientSingle;

import redis.clients.jedis.HostAndPort;

public class RedisTest {
	
	@Test
	public void jedisClientSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
		JedisClient jedisClient=applicationContext.getBean(JedisClient.class);
		jedisClient.set("ldz", "nan");
		System.out.println(jedisClient.get("ldz"));
	}
}
