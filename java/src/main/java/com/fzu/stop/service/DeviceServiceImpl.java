package com.fzu.stop.service;

import com.fzu.stop.dao.DeviceDao;
import com.fzu.stop.pojo.DeviceDO;
import com.fzu.stop.pojo.DeviceDTO;
import com.fzu.stop.pojo.DeviceInfoDTO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author 林浩然
 */
@Service
public class DeviceServiceImpl extends MessageListenerAdapter implements DeviceService{
	Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	DeviceDao deviceDao;
	@Autowired
	DeviceService deviceService;
	@Value("${rtmp.redis.key}")
	String key;

	@Override
	public List<DeviceDTO> listOnlineDevice(){
		Map<String,HashMap> deviceMap = (Map<String, HashMap>) redisTemplate.opsForValue().get(key);
		Collection<HashMap> devices = deviceMap.values();
		ArrayList<DeviceDTO> deviceList = new ArrayList<>();
		for (HashMap device : devices) {
			deviceList.add(new DeviceDTO(device));
		}
		return deviceList;
	}
	@Override
	public List<DeviceInfoDTO> listAllDevice(){
		List<DeviceDO> deviceDOList = deviceService.listDevices();
		List<DeviceDTO> deviceDTOList= listOnlineDevice();
		Map<String, DeviceDO> deviceDOMap = new HashMap<>();
		Map<String, DeviceDTO> deviceDTOMap = new HashMap<>();
		for (DeviceDO deviceDO : deviceDOList) {
			deviceDOMap.put(deviceDO.getDeviceName(),deviceDO);
		}
		for (DeviceDTO deviceDTO : deviceDTOList) {
			deviceDTOMap.put(deviceDTO.getLiveName(),deviceDTO);
		}

		List<DeviceInfoDTO> deviceInfoList= new ArrayList<>();
		//将所有已在数据库中的
		for (DeviceDO deviceDO : deviceDOList) {
			DeviceDTO deviceDTO = deviceDTOMap.get(deviceDO.getDeviceName());
			if(deviceDTO==null){
				deviceInfoList.add(new DeviceInfoDTO(deviceDO));
			}else {
				deviceInfoList.add(new DeviceInfoDTO(deviceDO,deviceDTO));
			}
		}
		for (DeviceDTO deviceDTO : deviceDTOList) {
			if(!deviceDOMap.containsKey(deviceDTO.getLiveName())){
				DeviceDO newDeviceDO = new DeviceDO("未命名设备", deviceDTO.getLiveName());
				deviceService.insertDevice(newDeviceDO);
				deviceInfoList.add(new DeviceInfoDTO(newDeviceDO,deviceDTO));
			}
		}
		return deviceInfoList;
	}

	@Override
	public List<DeviceDO> listDevices() {
		List<DeviceDO> deviceList = deviceDao.listDevices();
		return  deviceList;
	}

	@Override
	public DeviceDO getDeviceById(Integer id) {
		DeviceDO device= deviceDao.getDeviceById(id);
		return device;
	}

	@Override
	public List<DeviceDO> getDeviceByName(String name) {
		List<DeviceDO> deviceList= deviceDao.getDeviceByName(name);
		return deviceList;
	}

	@Override
	public DeviceDO getDeviceByDeviceName(String deviceName){
		DeviceDO device= deviceDao.getDeviceByDeviceName(deviceName);
		return device;
	}

	@Override
	public Integer deleteDeviceById(Integer id) {
		Integer row = deviceDao.deleteDeviceById(id);
		return row;
	}

	@Override
	public Integer insertDevice(DeviceDO deviceDO) {
		synchronized(deviceDao){
			Integer row = deviceDao.insertDevice(deviceDO);
			return row;
		}
	}

	@Override
	public Integer updateDeviceById(DeviceDO device) {
		Integer row = deviceDao.updateDeviceById(device);
		return row;
	}

	@Override
	public void onMessage(@NotNull Message message, byte[] pattern) {
		log.debug("redis订阅信息："+message);
		deviceService.listAllDevice();
	}

}
