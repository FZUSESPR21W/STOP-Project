package com.fzu.stop.dao;

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
     * @param start
     * @param end
     * @param id
     * @return
     */
    List<ParkingSituationDO> getParkingSituation(String start,String end, Integer id);

    /**
     * 近两小时停车情况
     * @param start
     * @param end
     * @param id
     * @return
     */
    Integer getHourlyParkingSituation(String start,String end, Integer id);

    /**
     * 获取start到end期间停车峰值数据
     * @param start
     * @param end
     * @return
     */
    List<ParkingSituationDO> getDailyParkingSituation(String start,String end);

    /**
     * 通过设备id获取点集合
     * @param id
     * @return
     */
    List<PointDO> getPointsByDeviceId(Integer id);


}
