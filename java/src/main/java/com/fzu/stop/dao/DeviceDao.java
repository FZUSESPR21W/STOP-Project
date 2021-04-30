package com.fzu.stop.dao;

import com.fzu.stop.pojo.DeviceDO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
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
	DeviceDO getDeviceByName(String name);

	/**
	 * 根据设备id删除设备
	 * @param id 设备id
	 */
	void deleteDeviceById(Integer id);

	/**
	 * 根据设备名删除设备
	 * @param name 设备名
	 */
	void deleteDeviceByName(String name);

	/**
	 * 插入设备
	 * @param device 设备
	 * @return 插入的设备
	 */
	DeviceDO insertDevice(DeviceDO device);

	/**
	 * 通过设备名更新设备信息
	 * @param name 设备名
	 * @param longitude 设备经度
	 * @param latitude 设备纬度
	 * @param maxCarsNumber 最大停车数
	 * @param url 设备url
	 */
	@Deprecated
	void updateDeviceByName(String name,BigDecimal longitude,BigDecimal latitude,Integer maxCarsNumber,String url);
	/**
	 * 通过设备id更新设备信息
	 * @param id 设备id
	 * @param name 设备名
	 * @param longitude 设备经度
	 * @param latitude 设备纬度
	 * @param maxCarsNumber 最大停车数
	 * @param url 设备url
	 */
	void updateDeviceById(Integer id,String name,BigDecimal longitude,BigDecimal latitude,Integer maxCarsNumber,String url);
}
