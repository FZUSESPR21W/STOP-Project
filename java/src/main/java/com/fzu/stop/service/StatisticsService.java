package com.fzu.stop.service;

import com.fzu.stop.pojo.ParkingSituationDO;
import com.fzu.stop.pojo.PointDO;

import java.util.List;

/**
 * @author 宋家锐
 */
public interface StatisticsService {
    Integer NUM = 6;
    /**
     * 获取最新停车情况
     * @return 停车情况
     */
    List<ParkingSituationDO> getParkingSituation(Integer id);
    /**
     * 按日获取停车数据
     * @return 每日停车情况
     */
    List<ParkingSituationDO> getDailyParkingSituation();

    /**
     * 根据设备id获取点集合
     * 按固定时间获取停车数据
     * @return 当日停车情况
     */
    List<Object> getHourParkingSituation();

    /**
     * 根据设备id获取点集合
     * @param id
     * @return
     */
    List<PointDO> getPointsByDeviceId(Integer id);

    /**
     * @return 设备相关信息
     */
    List<Object> getAllPoints();

}
