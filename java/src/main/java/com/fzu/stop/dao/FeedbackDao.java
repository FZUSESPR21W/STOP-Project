package com.fzu.stop.dao;

import com.fzu.stop.pojo.FeedbackDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 宋家锐
 */
@Mapper
public interface FeedbackDao {
    /**
     * 根据页码和每页条数查询反馈
     * @param start
     * @param lim
     * @return
     */
    List<FeedbackDO> getFeedbackList(int start,int lim);

    /**
     * 根据id获取反馈详细信息
     * @param id
     * @return
     */
    FeedbackDO getFeedback(int id);

    /**
     * 更新反馈状态
     * @param id
     * @param status
     * @return
     */
    int updateFeedback(int id,int status);

    /**
     * 添加一条反馈
     * @param feedbackDO
     * @return
     */
    boolean addFeedback(FeedbackDO feedbackDO);

}
