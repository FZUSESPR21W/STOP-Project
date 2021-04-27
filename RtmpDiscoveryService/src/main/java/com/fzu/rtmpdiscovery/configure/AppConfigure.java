package com.fzu.rtmpdiscovery.configure;

import com.fzu.rtmpdiscovery.service.RtmpDiscoveryService;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yrc
 */
@Component
public class AppConfigure {
    @Bean
    public HttpClient getHttpClient(){
        HttpClient client = HttpClientBuilder
                .create()
                .build();
        return client;
    }

    /**
     * 定义一个公用的线程池
     * @return 线程池
     */
    @Bean
    public ThreadPoolExecutor getThreadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1
                ,3
                ,1
                , TimeUnit.HOURS
                ,new LinkedBlockingQueue<>()
                ,new DefaultThreadFactory("discovery"));
        return threadPoolExecutor;
    }
    @Bean
    public DateFormat getSimpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @return 用来存储服务器ip列表的set
     */
    @Bean("ipSet")
    public Set<String> getIpSet(){
        return  new CopyOnWriteArraySet<>();
    }
}
