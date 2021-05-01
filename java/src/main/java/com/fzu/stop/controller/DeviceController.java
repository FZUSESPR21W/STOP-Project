package com.fzu.stop.controller;

import com.fzu.stop.Util.ResponseUtil;
import com.fzu.stop.pojo.DeviceDTO;
import com.fzu.stop.pojo.DeviceInfoDTO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林浩然
 */
@Controller
@Validated
@RequestMapping("/device")
public class DeviceController {
	@Autowired
	DeviceService deviceService;
	@Operation(description = "从redis获取的当前在线的设备列表")
	@GetMapping(value = "/get_online_device_list")
	@ResponseBody
	public ResponseDTO getOnlineDeviceList(){
		List<DeviceDTO> deviceDTOList = deviceService.listOnlineDevice();
		HashMap<String, Object> resp = new HashMap<>();
		resp.put("deviceList", deviceDTOList);
		return ResponseUtil.getSuccessResponse("",resp);
	}

	@Operation(description = "获取所有设备列表")
	@GetMapping(value = "/get_all_device")
	@ResponseBody
	public ResponseDTO getDeviceInfo(){
		List<DeviceInfoDTO> deviceInfoDTOList = deviceService.listAllDevice();
		HashMap<String, Object> resp = new HashMap<>();
		resp.put("deviceList", deviceInfoDTOList);
		return ResponseUtil.getSuccessResponse("",resp);
	}
	@Operation(description = "通过设备id删除设备")
	@PostMapping(value = "/delete_device_by_id")
	@ResponseBody
	public ResponseDTO deleteDeviceById(@NotNull(message = "id不能为空") Integer id){
		Integer row = deviceService.deleteDeviceById(id);
		return ResponseUtil.getSuccessResponse("执行成功，影响行数："+row,new HashMap<>());
	}
	@Operation(description = "通过设备id更新设备信息")
	@PostMapping(value = "/update_device_by_id")
	@ResponseBody
	public ResponseDTO updateDeviceById(@NotNull(message = "id不能为空") Integer id, String name,
										BigDecimal longitude, BigDecimal latitude,
										Integer maxCarsNumber, String deviceName) {
		Integer row = deviceService.updateDeviceById(id, name, longitude, latitude, maxCarsNumber, deviceName);
		return ResponseUtil.getSuccessResponse("执行成功，影响行数："+row,new HashMap<>());
	}



}
