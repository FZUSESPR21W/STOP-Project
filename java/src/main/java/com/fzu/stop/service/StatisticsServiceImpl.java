package com.fzu.stop.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fzu.stop.dao.DeviceDao;
import com.fzu.stop.dao.StatisticsDao;
import com.fzu.stop.pojo.DeviceDO;
import com.fzu.stop.pojo.DeviceInfoDTO;
import com.fzu.stop.pojo.ParkingSituationDO;
import com.fzu.stop.pojo.PointDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 宋家锐
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    StatisticsDao statisticsDao;
    @Autowired
    DeviceDao deviceDao;
    @Autowired
    DeviceService deviceService;
    @Autowired
    StatisticsService statisticsService;

    @Override
    public List<ParkingSituationDO> getParkingSituation(Integer id) {
        Date date = new Date();
        Date startTime = new Date(date.getTime() - 2 * 60 * 60 * 1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return statisticsDao.getParkingSituation(df.format(startTime), df.format(date), id);
    }

    @Override
    public List<Object> getHourParkingSituation() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        List<Integer> list;

        Map<String, Object> map;
        List<Object> req = new ArrayList<>();
        List<DeviceDO> deviceDOList = deviceDao.listDevices();
        for (DeviceDO deviceDO : deviceDOList) {
            Integer id = deviceDO.getId();
            list = new ArrayList<>();
            map = new HashMap<>(16);
            map.put("name", deviceDO.getName());
            Date time = new Date(date.getTime() - 2 * 60 * 60 * 1000);
            for (int i = 0; i < NUM; i++) {
                Date start = new Date(time.getTime() - 2 * 60 * 60 * 1000);
                Integer value = statisticsDao.getHourlyParkingSituation(df.format(start), df.format(time), id);
                list.add(value == null ? 0 : value);
                time = new Date(time.getTime() + 20 * 60 * 1000);
            }
            map.put("values", list);
            req.add(map);
        }
        return req;
    }

    @Override
    public List<ParkingSituationDO> getDailyParkingSituation() {
        Date date = new Date();
        Date startTime = new Date(date.getTime() - 24 * 60 * 60 * 1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return statisticsDao.getDailyParkingSituation(df.format(startTime), df.format(date));
    }

    @Override
    public List<PointDO> getPointsByDeviceId(Integer id) {
        List<PointDO> pointDOS = statisticsDao.getPointsByDeviceId(id);
        return pointDOS;
    }

    @Override
    public List<Object> getAllPoints() {
        Map<String, Object> map;
        List<Object> req = new ArrayList<>();
        List<DeviceInfoDTO> deviceInfoDTOList = deviceService.listAllDevice();
        for (DeviceInfoDTO deviceInfoDTO : deviceInfoDTOList) {
            map = new HashMap<>(4);
            map.put("online", deviceInfoDTO.getOnline());
            Integer id = deviceInfoDTO.getDeviceDO().getId();
            List<ParkingSituationDO> parkingSituationDOList=statisticsService.getParkingSituation(id);
            map.put("value", parkingSituationDOList.size() > 0 ? parkingSituationDOList.get(0).getValue() : -1);
            map.put("maxValue", deviceInfoDTO.getDeviceDO().getMaxCarsNumber());
            List<PointDO> pointDOList = statisticsService.getPointsByDeviceId(id);
            JSONArray jsonArray = null;
            if (pointDOList.size() > 0) {
                jsonArray=new JSONArray();
                for (PointDO pointDO : pointDOList) {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("latitude",pointDO.getLatitude());
                    jsonObject.put("longitude",pointDO.getLongitude());
                    jsonArray.add(jsonObject);
                }
            }
            map.put("points",jsonArray);
            req.add(map);
        }
        return req;
    }
}
