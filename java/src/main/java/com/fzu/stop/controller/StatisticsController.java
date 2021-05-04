package com.fzu.stop.controller;

import com.fzu.stop.Util.ResponseUtil;
import com.fzu.stop.pojo.ParkingSituationDO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * @author 宋家锐
 */
@Controller
@RequestMapping("/api")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;
    @GetMapping("/stop_status")
    @ResponseBody
    public ResponseDTO getParkingSituation(){
        List<ParkingSituationDO> parkingSituationDOList=statisticsService.getParkingSituation();
        if (parkingSituationDOList.size() > 0) {
            Map<String, Object> data = new HashMap<>(parkingSituationDOList.size());
            data.put("stopStatusList", parkingSituationDOList);
            return ResponseUtil.getSuccessResponse("获取成功", data);
        }
        return ResponseUtil.getFailResponse("获取失败", new HashMap<>(16));
    }

    @GetMapping("/stop_status_daily")
    @ResponseBody
    public ResponseDTO getDailyParkingStatistics(){
        List<ParkingSituationDO> dailyParkingSituation=statisticsService.getDailyParkingSituation();
        if (dailyParkingSituation.size() > 0) {
            Map<String, Object> data = new HashMap<>(dailyParkingSituation.size());
            data.put("stopStatusDaily", dailyParkingSituation);
            return ResponseUtil.getSuccessResponse("获取成功", data);
        }
        return ResponseUtil.getFailResponse("获取失败", new HashMap<>(16));
    }

}
