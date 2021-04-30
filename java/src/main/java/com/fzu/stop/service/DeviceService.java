package com.fzu.stop.service;

import com.fzu.stop.pojo.DeviceDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.List;

/**
 * @author 林浩然
 */
public interface DeviceService extends MessageListener {
	/**
	 * 从redis获取device列表
	 * @return redis的device列表
	 */
	List<DeviceDTO> listDevice();
	/**
	 * redis订阅事件
	 * @param message 订阅发布收到的信息
	 * @param bytes bytes
	 */
	@Override
	void onMessage(@NotNull Message message, byte[] bytes);
}
