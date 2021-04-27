package com.fzu.stop.service;

import com.fzu.stop.pojo.FeedbackDO;

/**
 * @author 宋家锐
 */
public interface FeedbackService {
    /**
     * 获取反馈列表
     * @param page
     * @param limit
     * @return 反馈列表
     */
    Object getFeedbackList(int page,int limit);

}
