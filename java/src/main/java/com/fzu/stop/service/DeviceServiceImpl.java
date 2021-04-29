package com.fzu.stop.service;

import com.fzu.stop.pojo.DeviceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 林浩然
 */
@Service
public class DeviceServiceImpl extends MessageListenerAdapter implements DeviceService{
	Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);
	@Autowired
	RedisTemplate redisTemplate;
	@Value("${rtmp.redis.key}")
	String key;

	@Override
	public List<DeviceDTO> listDevice(){
		Map<String,HashMap> deviceMap = (Map<String, HashMap>) redisTemplate.opsForValue().get(key);
		Collection<HashMap> devices = deviceMap.values();
		ArrayList<DeviceDTO> deviceList = new ArrayList<>();
		for (HashMap device : devices) {
			deviceList.add(new DeviceDTO(device));
		}
		return deviceList;
	}
	@Override
	public void onMessage(Message message, byte[] pattern) {
		log.debug("redis订阅信息："+message);
	}

}
