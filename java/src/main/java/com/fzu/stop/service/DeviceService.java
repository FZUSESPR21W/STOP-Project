package com.fzu.stop.service;

import com.fzu.stop.pojo.DeviceDO;
import com.fzu.stop.pojo.DeviceDTO;
import com.fzu.stop.pojo.DeviceInfoDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 林浩然
 */
public interface DeviceService extends MessageListener {
	/**
	 * 从redis获取device列表
	 * @return redis的device列表
	 */
	List<DeviceDTO> listOnlineDevice();

	/**
	 * 获取所有的设备信息
	 * 如果在数据库中且在redis中就为在线
	 * 如果在数据库中但不再redis中为离线
	 * 如果不在数据库中但在redis中就在数据库中插入一个新设备
	 * @return 所有设备列表
	 */
	List<DeviceInfoDTO> listAllDevice();
	/**
	 * 获取所有设备列表
	 * @return 所有设备列表
	 */
	List<DeviceDO> listDevices();

	/**
	 * 根据设备id获取设备
	 * @param id 设备id
	 * @return DeviceDO
	 */
	DeviceDO getDeviceById(Integer id);

	/**
	 * 根据设备名获取设备
	 * @param name 设备名
	 * @return DeviceDO列表
	 */
	List<DeviceDO> getDeviceByName(String name);


	/**
	 * 根据设备名获取设备
	 * @param deviceName 设备名
	 * @return DeviceDO
	 */
	DeviceDO getDeviceByDeviceName(String deviceName);

	/**
	 * 根据设备id删除设备
	 * @param id 设备id
	 * @return 受影响的行数
	 */
	Integer deleteDeviceById(Integer id);

	/**
	 * 插入设备
	 * @param device 设备
	 * @return 受影响的行数
	 */
	Integer insertDevice(DeviceDO device);

	/**
	 * 通过设备id更新设备信息
	 * @param id 设备id
	 * @param name 设备名
	 * @param longitude 设备经度
	 * @param latitude 设备纬度
	 * @param maxCarsNumber 最大停车数
	 * @param deviceName 物理设备名
	 * @return 受影响的行数
	 */
	Integer updateDeviceById(Integer id,String name,BigDecimal longitude,BigDecimal latitude,Integer maxCarsNumber,String deviceName);

	/**
	 * redis事件订阅监听器
	 * @param message 收到的消息
	 * @param bytes bytes
	 */
	@Override
	void onMessage(@NotNull Message message, byte[] bytes);
}
