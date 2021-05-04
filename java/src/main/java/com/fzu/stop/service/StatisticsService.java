package com.fzu.stop.service;

import com.fzu.stop.pojo.ParkingSituationDO;

import java.util.List;

/**
 * @author 宋家锐
 */
public interface StatisticsService {
    /**
     * 获取最新停车情况
     * @return 停车情况
     */
    List<ParkingSituationDO> getParkingSituation();
    /**
     * 按日获取停车数据
     * @return 每日停车情况
     */
    List<ParkingSituationDO> getDailyParkingSituation();

}
