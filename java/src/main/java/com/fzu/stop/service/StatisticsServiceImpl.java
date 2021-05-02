package com.fzu.stop.service;

import com.fzu.stop.dao.StatisticsDao;
import com.fzu.stop.pojo.ParkingSituationDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 宋家锐
 */
@Service
public class StatisticsServiceImpl implements StatisticsService{
    @Autowired
    StatisticsDao statisticsDao;

    @Override
    public List<ParkingSituationDO> getParkingSituation(){
        return statisticsDao.getParkingSituation();
    }
}
