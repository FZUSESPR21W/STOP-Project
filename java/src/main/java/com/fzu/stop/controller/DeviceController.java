package com.fzu.stop.controller;

import com.fzu.stop.Util.ResponseUtil;
import com.fzu.stop.pojo.DeviceDTO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林浩然
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
	@Autowired
	DeviceService deviceService;
	@GetMapping("/get_online_device_list")
	@ResponseBody
	public ResponseDTO getDeviceList(){
		List<DeviceDTO> deviceDTOList = deviceService.listDevice();
		HashMap<String, Object> resp = new HashMap<>();
		resp.put("deviceList", deviceDTOList);
		return ResponseUtil.getSuccessResponse("",resp);
	}
}
