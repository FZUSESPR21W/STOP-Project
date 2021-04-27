package com.fzu.stop.service;


import com.fzu.stop.dao.FeedbackDao;
import com.fzu.stop.pojo.FeedbackDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 宋家锐
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    FeedbackDao feedbackDao;

    @Override
    public Object getFeedbackList(int page,int limit){
        int start=(page-1)*limit;
        return feedbackDao.getFeedbackList(start,limit);
    }
}
