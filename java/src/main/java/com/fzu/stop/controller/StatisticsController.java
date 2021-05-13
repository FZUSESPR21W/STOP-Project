package com.fzu.stop.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.stop.util.ResponseUtil;
import com.fzu.stop.util.WechatUtil;
import com.fzu.stop.pojo.ParkingSituationDO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.StatisticsService;
import com.fzu.stop.valid.annotation.IntegerGroup;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Min;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.*;


/**
 * @author 宋家锐、梁达毅、武雍易
 */
@Controller
@RequestMapping("/api")
@Validated
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;
    @GetMapping("/stop_status")
    @ResponseBody
    public ResponseDTO getParkingSituation(Integer id){
        List<ParkingSituationDO> parkingSituationDOList=statisticsService.getParkingSituation(id);
        if (parkingSituationDOList.size() > 0) {
            Map<String, Object> data = new HashMap<>(parkingSituationDOList.size());
            data.put("stopStatusList", parkingSituationDOList);
            return ResponseUtil.getSuccessResponse("获取成功", data);
        }
        return ResponseUtil.getFailResponse("获取失败", new HashMap<>(16));
    }

    @GetMapping("/stop_status_hourly")
    @ResponseBody
    public ResponseDTO getHourParkingStatistics(){
        List<Object> hourParkingSituation=statisticsService.getHourParkingSituation();
        if (hourParkingSituation.size() > 0) {
            Map<String, Object> data = new HashMap<>(hourParkingSituation.size());
            data.put("stopStatusHourly", hourParkingSituation);
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
        return ResponseUtil.getFailResponse("暂无数据", new HashMap<>(16));
    }

    @GetMapping("/get_login_list")
    @ResponseBody
    public ResponseDTO getLoginList(@IntegerGroup(integerGroup = {0,6,29},message = "非法范围") Integer delta) throws IOException {
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

    @Operation(description = "获取最近delta+1天的每天小程序访问次数")
    @GetMapping("/get_visit_number")
    @ResponseBody
    public ResponseDTO getVisitNumber(@Min(value = 1,message = "要大于0") Integer delta) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        //允许设置的最大值为昨日
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (int i = 0; i < delta + 1 ;i++) {
            calendar.add(Calendar.DAY_OF_YEAR, -i);
            Date date = calendar.getTime();
            String beginDate = simpleDateFormat.format(date);
            jsonObjectList.add(WechatUtil.getDailyVisitTrend(beginDate, beginDate));
        }
        Map<String,Object> data =new HashMap<>(1);
        data.put("visitNumber",jsonObjectList);
        return ResponseUtil.getSuccessResponse("获取成功",data);

    }
}
