package com.fzu.stop.dao;

import com.fzu.stop.pojo.DeviceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 林浩然
 */
@Mapper
public interface DeviceDao {
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
	 * @return DeviceDO
	 */
	List<DeviceDO> getDeviceByName(String name);

	/**
	 * 根据设备名获取设备
	 * @param deviceName 物理设备名
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
	 * @return 插入的设备
	 * @return 受影响的行数
	 */
	Integer insertDevice(DeviceDO device);

	/**
	 * 通过设备id更新设备信息
	 * @param device 要更新的设备信息
	 */
	Integer updateDeviceById(DeviceDO device);
}
