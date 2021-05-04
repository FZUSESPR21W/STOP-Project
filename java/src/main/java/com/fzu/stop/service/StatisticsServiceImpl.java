package com.fzu.stop.service;

import com.fzu.stop.dao.StatisticsDao;
import com.fzu.stop.pojo.ParkingSituationDO;
import org.apache.ibatis.javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public List<ParkingSituationDO> getDailyParkingSituation(){
        Date date = new Date();
        Date startTime = new Date(date.getTime()-7*24*60*60*1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return statisticsDao.getDailyParkingSituation(df.format(startTime), df.format(date));
    }

}
