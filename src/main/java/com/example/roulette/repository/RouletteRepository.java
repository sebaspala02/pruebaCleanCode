package com.example.roulette.repository;

import java.util.Date;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.roulette.model.Roulette;

@Repository
public class RouletteRepository extends RedisRepository<Roulette>{
	public RouletteRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "Roulette");
    }

    public void openRoulette(String id) throws Exception {
        Roulette roulette = this.hashOperations.get(this.key, id);
        if (roulette == null) throw new NullPointerException("Roulette not found");
        if (roulette.getStatus().equalsIgnoreCase("open")) throw new Exception("roulette was already opened");
        roulette.setStatus("open");
        roulette.setUpdateAt(new Date());
        this.edit(id, roulette);
    }

    public void closeRoulette(String id) throws Exception {
        Roulette roulette = this.hashOperations.get(this.key, id);
        if (roulette == null) throw new NullPointerException("Roulette not found");
        if (roulette.getStatus().equalsIgnoreCase("close")) throw new Exception("roulette was already closed");
        roulette.setStatus("close");
        roulette.setUpdateAt(new Date());
        this.edit(id, roulette);
    }
}
