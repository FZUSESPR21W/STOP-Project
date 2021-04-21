package com.fzu.rtmpfinder.controller;

import com.fzu.rtmpfinder.pojo.HlsLive;
import com.fzu.rtmpfinder.service.RtmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RtmpController {
    @Autowired
    RtmpService service;

    @GetMapping("/rtmp_info")
    @ResponseBody
    public Map<String,Object> getRtmpInfo(){
        return service.getRtmpInfo();
    }
    @GetMapping("/hls_list")
    @ResponseBody
    public Map<String,Object> getHlsList(){
        List<HlsLive> hlsList = service.getHlsList();
        Map<String, Object> map = new HashMap<>();
        map.put("data",hlsList);
        return map;
    }
}
