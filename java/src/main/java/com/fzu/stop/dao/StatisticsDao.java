package com.fzu.stop.dao;

import com.fzu.stop.pojo.BlockDO;
import com.fzu.stop.pojo.ParkingSituationDO;
import com.fzu.stop.pojo.PointDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 宋家锐
 */
@Mapper
public interface StatisticsDao {
    /**
     * 获取所有地点最新停车情况
     * @param time
     * @param id
     * @return
     */
    List<ParkingSituationDO> getParkingSituation(String start,String end, Integer id);

    /**
     * 获取所有地点最新停车情况
     * @param time
     * @param id
     * @return
     */
    Integer getHourlyParkingSituation(String time, Integer id);

    /**
     * 获取start到end期间每日停车数据
     * @param start
     * @param end
     * @return
     */
    List<ParkingSituationDO> getDailyParkingSituation(String start,String end);

    /**
     * 通过设备id获取区域块
     * @param id
     * @return
     */
    BlockDO getBlockByDeviceId(Integer id);

    /**
     * 通过区域块id获取点的集合
     * @param id
     * @return
     */
    List<PointDO> getPointsByBlockId(Integer id);
}
