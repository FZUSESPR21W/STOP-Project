package com.fzu.stop.dao;

import com.fzu.stop.pojo.FeedbackDO;
import com.fzu.stop.pojo.ParkingSituationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 宋家锐
 */
@Mapper
public interface StatisticsDao {
    /**
     * 获取所有地点最新停车情况
     * @return
     */
    List<ParkingSituationDO> getParkingSituation();
}
