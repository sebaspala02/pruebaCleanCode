package com.example.roulette.repository;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class RedisRepository<T> {
	protected final RedisTemplate<String, T> redisTemplate;
	protected final String key;
	protected HashOperations<String, String, T> hashOperations;

	public RedisRepository(RedisTemplate<String, Object> redisTemplate, String key) {
		this.redisTemplate = (RedisTemplate<String, T>) redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
		this.key = key;
	}

	public Map<String, T> findAll() {
		return this.hashOperations.entries(this.key);
	}

	public T findById(String id) {
		return this.hashOperations.get(this.key, id);
	}

	public String save(T object) {
		String uuid = UUID.randomUUID().toString();
		this.hashOperations.put(this.key, uuid, object);

		return uuid;
	}

	public void edit(String id, T object) {
		this.hashOperations.put(this.key, id, object);
	}
}
