package com.fzu.rtmpfinder.redis;

import com.fzu.rtmpfinder.pojo.HlsLive;
import com.fzu.rtmpfinder.service.RtmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

public class  RtmpDiscovery implements Runnable {
    private RedisTemplate redisTemplate;
    private ThreadPoolExecutor threadPoolExecutor;
    private RtmpService rtmpService;
    private static RtmpDiscovery instance = new RtmpDiscovery();
    private Boolean started=false;
    @Value("${rtmp.redis.key}")
    private String redisKey;
    @Value("${rtmp.redis.channel}")
    private String channel;
    @Autowired
    private DateFormat dataFormat;
    private Logger log=LoggerFactory.getLogger(RtmpDiscovery.class);
    private RtmpDiscovery(){
    }
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Autowired
    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }
    @Autowired
    public void setRtmpService(RtmpService rtmpService) {
        this.rtmpService = rtmpService;
    }

    public static RtmpDiscovery getInstance() {
        return instance;
    }

    public void startDiscover(){
        synchronized (started){
            if(!started){
                threadPoolExecutor.execute(instance);
                started=true;
            }
        }
    }

    private boolean compareMap(Map c1,Map c2){
        boolean flag=true;
        if(c1.size()==c2.size()){
            for (Object o : c1.keySet()) {
                if(!c2.containsKey(o)){
                    flag=false;
                    break;
                }else{
                    if(((HlsLive)c1.get(o)).compareTo((HlsLive)c2.get(o))!=0){
                        flag=false;
                        break;
                    }
                }
            }
        }else{
            flag=false;
        }
        return flag;
    }
    public Map getDevice(){
        Map map=null;
        if (redisTemplate.hasKey(redisKey)) {
            map = (Map) redisTemplate.opsForValue().get(redisKey);
        }else {
            map=new HashMap<>();
        }
        return map;
    }
    public void publishNewDevice(Map value){
        redisTemplate.opsForValue().set(redisKey,value);
        String format = dataFormat.format(System.currentTimeMillis());
        redisTemplate.convertAndSend(channel,"Device status changed at "+format);
        log.info("Device status changed at "+format);
    }
    public void run() {
        for(;;){
            Map newDevice = rtmpService.getHlsLiveMap();
            Map oldDevice = getDevice();
            if(!compareMap(oldDevice,newDevice)){
                publishNewDevice(newDevice);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
