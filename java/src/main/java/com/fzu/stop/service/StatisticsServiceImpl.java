package com.fzu.stop.service;

import com.fzu.stop.dao.DeviceDao;
import com.fzu.stop.dao.StatisticsDao;
import com.fzu.stop.pojo.BlockDO;
import com.fzu.stop.pojo.DeviceDO;
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

    @Override
    public List<ParkingSituationDO> getParkingSituation(Integer id) {
        Date date = new Date();
        Date startTime = new Date(date.getTime() - 24 * 60 * 60 * 1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        System.out.println(df.format(date));
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
                Integer value = statisticsDao.getHourlyParkingSituation(df.format(time), id);
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
    public BlockDO getBlockByDeviceId(Integer id) {
        BlockDO blockDO = new BlockDO();
        blockDO = statisticsDao.getBlockByDeviceId(id);
        Integer blockId = blockDO.getId();
        List<PointDO> pointDOS = statisticsDao.getPointsByBlockId(blockId);
        blockDO.setPoints(pointDOS);
        return blockDO;
    }

}
