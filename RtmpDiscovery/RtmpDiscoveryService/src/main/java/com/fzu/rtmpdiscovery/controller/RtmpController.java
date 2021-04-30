package com.fzu.rtmpdiscovery.controller;

import com.fzu.rtmpdiscovery.service.RtmpService;
import com.fzu.rtmpdiscovery.pojo.HlsLive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yrc
 */
@Controller
public class RtmpController {
    @Autowired
    RtmpService service;
    Logger log = LoggerFactory.getLogger(RtmpController.class);

    /**
     * @return nginx推流服务器的原始数据
     */
    @GetMapping("/rtmp_info")
    @ResponseBody
    public Map<String,Object> getRtmpInfo(){
        return service.getRtmpInfo();
    }

    /**
     * @return 所有服务器的推流列表
     */
    @GetMapping("/hls_list")
    @ResponseBody
    public Map<String,Object> getHlsList(){
        List<HlsLive> hlsList = service.getAllHlsList();
        Map<String, Object> map = new HashMap<>();
        map.put("data",hlsList);
        return map;
    }

    /**
     * @param req
     * @return 访问客户端的ip
     */
    @GetMapping("/get_client_ip")
    @ResponseBody
    public String getClientIp(HttpServletRequest req){

        String remoteHost = req.getRemoteHost();
        log.info("服务器："+remoteHost+"上线");
        return remoteHost;
    }
}
