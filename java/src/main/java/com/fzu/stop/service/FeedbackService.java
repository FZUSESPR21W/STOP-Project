package com.fzu.stop.service;

import com.fzu.stop.pojo.FeedbackDO;

import java.util.List;

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
    List<FeedbackDO> getFeedbackList(int page, int limit);

    /**
     * 获取一条反馈记录
     * @param id
     * @return 反馈详细
     */
    Object getFeedback(int id);

    /**
     * 更新反馈状态
     * @param id
     * @param status
     * @return 反馈详细
     */
    int updateFeedback(int id,int status);

}
