package com.fzu.rtmpdiscovery;

import com.fzu.rtmpdiscovery.redis.RtmpDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RtmpDiscoveryApplication {
    @Autowired
    RtmpDiscovery discovery;

    public static void main(String[] args) {
        SpringApplication.run(RtmpDiscoveryApplication.class, args);
    }
    @Autowired
    RedisTemplate redisTemplate;
    @PostConstruct
    public void init(){
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setKeySerializer(RedisSerializer.string());
        discovery.startDiscover();
    }

}
