package com.fzu.rtmpfinder.configure;

import com.fzu.rtmpfinder.redis.RtmpDiscovery;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class AppConfigure {
    @Bean
    public HttpClient getHttpClient(){
        HttpClient client = HttpClientBuilder.create().build();
        return client;
    }
    @Bean
    public ThreadPoolExecutor getThreadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1,1,10, TimeUnit.HOURS,new LinkedBlockingQueue<>());
        return threadPoolExecutor;
    }
    @Bean
    public RtmpDiscovery getRempDiscovery(){
        return RtmpDiscovery.getInstance();
    }
    @Bean
    public DateFormat getSimpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
