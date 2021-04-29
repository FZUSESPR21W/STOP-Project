package com.fzu.rtmpdiscovery;

import com.fzu.rtmpdiscovery.service.RtmpDiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

/**
 * @author yrc
 */
@SpringBootApplication
public class RtmpDiscoveryApplication {
    @Autowired
    RtmpDiscoveryService discoveryService;

    public static void main(String[] args) {
        SpringApplication.run(RtmpDiscoveryApplication.class, args);
    }

    /**
     * 开启服务
     */
    @PostConstruct
    public void init(){
        discoveryService.startDiscover();
    }

}
