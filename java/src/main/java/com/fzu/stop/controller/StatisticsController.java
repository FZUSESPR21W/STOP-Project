package com.fzu.stop.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.stop.Util.ResponseUtil;
import com.fzu.stop.Util.WechatUtil;
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
import java.io.IOException;
import java.util.*;


/**
 * @author 宋家锐、梁达毅
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

    @PostMapping("/get_login_list")
    @ResponseBody
    public ResponseDTO getLoginList(Integer delta) throws IOException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        //允许设置的最大值为昨日
        Date cur_date=calendar.getTime();
        //开始日期与结束日期相差的天数限定为0/6/29，分别表示查询最近1/7/30天数据,即delta只能为0、6、29
        calendar.add(Calendar.DAY_OF_YEAR,-delta);
        Date past_date=calendar.getTime();
        String begin_date=simpleDateFormat.format(past_date);
        String end_date=simpleDateFormat.format(cur_date);
        JSONObject jsonObject=WechatUtil.getUserPortrait(begin_date,end_date);
        Map<String,Object> data =new HashMap<>();
        data.put("data",jsonObject);
        return ResponseUtil.getSuccessResponse("获取成功",data);

    }

}
