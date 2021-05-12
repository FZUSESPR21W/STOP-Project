package com.fzu.stop.service;

import com.fzu.stop.dao.DeviceDao;
import com.fzu.stop.pojo.DeviceDO;
import com.fzu.stop.pojo.DeviceDTO;
import com.fzu.stop.pojo.DeviceInfoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeviceServiceTest {
	@Autowired
	DeviceService deviceService;

	List<DeviceDO> deviceTestList;
	@BeforeEach
	void beforeEach(){
		deviceTestList=new ArrayList<>(2);
		deviceTestList.add(new DeviceDO("insertTest","testDevice1"));
		deviceTestList.add(new DeviceDO("insertTest","testDevice2"));
		deviceService.insertDevice(deviceTestList.get(0));
		deviceService.insertDevice(deviceTestList.get(1));
	}
	@AfterEach
	void  afterEach(){
		deviceService.deleteDeviceById(deviceTestList.get(0).getId());
		deviceService.deleteDeviceById(deviceTestList.get(1).getId());
	}

	@Test
	void listDevices() {
		List<DeviceDO> deviceDOList = deviceService.listDevices();
		List<DeviceDTO> deviceDTOList = deviceService.listOnlineDevice();
		List<DeviceInfoDTO> deviceInfoDTOS = deviceService.listAllDevice();
		Assertions.assertTrue(deviceDOList.size()<=deviceInfoDTOS.size());
		Assertions.assertTrue(deviceDTOList.size()<=deviceInfoDTOS.size());
	}

	@Test
	void getDeviceById() {
		DeviceDO device = deviceService.getDeviceById(deviceTestList.get(0).getId());
		Assertions.assertEquals("insertTest",device.getName());
	}

	@Test
	void getDeviceByName() {
		List<DeviceDO> devices = deviceService.getDeviceByName(deviceTestList.get(0).getName());
		Assertions.assertEquals(deviceTestList.get(0).getId(),devices.get(0).getId());
		Assertions.assertEquals(deviceTestList.get(1).getId(),devices.get(1).getId());
	}

	@Test
	void getDeviceByDeviceName() {
		DeviceDO device= deviceService.getDeviceByDeviceName(deviceTestList.get(0).getDeviceName());
		Assertions.assertEquals(deviceTestList.get(0).getId(),device.getId());
	}

	@Test
	void updateDeviceById() {
		deviceTestList.get(0).setDeviceName("changeDeviceName");
		Integer row = deviceService.updateDeviceById(deviceTestList.get(0));
		DeviceDO device= deviceService.getDeviceById(deviceTestList.get(0).getId());
		Assertions.assertEquals(1,row);
		Assertions.assertEquals(deviceTestList.get(0).getDeviceName(),device.getDeviceName());
	}

}