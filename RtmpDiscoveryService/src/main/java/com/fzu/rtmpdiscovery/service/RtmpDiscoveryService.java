package com.fzu.rtmpdiscovery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 轮询rtmp推流服务器获取流列表，并放入redis
 * @author yrc
 */
@Service
public class RtmpDiscoveryService implements Runnable {

    private Logger log=LoggerFactory.getLogger(RtmpDiscoveryService.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private RtmpService rtmpService;
    @Autowired
    Set<String> ipSet;
    @Autowired
    private DateFormat dataFormat;

    /**
     * redisKey：设置所有hls流信息的key
     * channel：发布信息的channel名
     * ipKey：获取上线服务器ip的key前缀
     */
    @Value("${rtmp.redis.key}")
    private String redisKey;
    @Value("${rtmp.redis.channel}")
    private String channel;
    @Value("${rtmp.redis.ipKey}")
    private String ipKey;

    private Boolean started;

    public RtmpDiscoveryService(){
    	started=false;
    }


    /**
     * 开启发现服务
     */
    public void startDiscover(){
        synchronized (started){
            if(!started){
                threadPoolExecutor.execute(this);
                started=true;
            }
            log.info("启动rtmp推流发现");
        }
    }

    /**
     * 从redis获取推流列表，如果没有就新建一个map并返回
     * @return 将redis中存储的流列表转换成map
     */
    public Map<String,Object> getDevice(){
        Map<String,Object> map=null;
        if (redisTemplate.hasKey(redisKey)) {
            map = (Map<String,Object>) redisTemplate.opsForValue().get(redisKey);
        }else {
            map=new HashMap<>();
        }
        return map;
    }

    /**
     * 将新的rtmp流集合保存到redis并发布
     * @param value 新的rtmp流集合
     */
    public void publishNewDevice(Map<String,Object> value){
        redisTemplate.opsForValue().set(redisKey,value);
        String format = dataFormat.format(System.currentTimeMillis());
        redisTemplate.convertAndSend(channel,"Device status changed at "+format);
        log.info("发现rtmp推流状态改变，正在推流："+value.keySet());
    }

    @Override
    public void run() {
        for(;;){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            upDateIpSet();
            Map newDevice = rtmpService.getHlsLiveMap();
            Map oldDevice = getDevice();
            if(!oldDevice.equals(newDevice)){
                publishNewDevice(newDevice);
            }
        }
    }

    /**
     * 更新在线服务器列表
     */
    private void upDateIpSet(){
        Set<String> keys = redisTemplate.keys(ipKey);
        Set<String> newIpSet = new CopyOnWriteArraySet<>();
        for (String key : keys) {
            String ip = (String) redisTemplate.opsForValue().get(key);
            newIpSet.add(ip);
        }
        if(!ipSet.equals(newIpSet)){
            ipSet.clear();
            ipSet.addAll(newIpSet);
        }
    }
}
