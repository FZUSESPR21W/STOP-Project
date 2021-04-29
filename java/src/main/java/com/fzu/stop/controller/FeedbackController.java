package com.fzu.stop.controller;

import com.fzu.stop.Util.ResponseUtil;
import com.fzu.stop.pojo.FeedbackDO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 宋家锐
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/get_feedback_list")
    @ResponseBody
    public ResponseDTO getFeedbackList(int page, int limit) {
        List<FeedbackDO> feedbackList = feedbackService.getFeedbackList(page, limit);
        Map<String, Object> data = new HashMap<>(limit);
        data.put("feedbackList", feedbackList);
        if (feedbackList.size() > 0) {
            return ResponseUtil.getSuccessResponse("获取成功", data);
        }
        return ResponseUtil.getFailResponse("获取失败", new HashMap<>(16));
    }

    @PostMapping("/detail")
    @ResponseBody
    public ResponseDTO getFeedback(int id) {
        if (id > 0) {
            Object feedback = feedbackService.getFeedback(id);
            Map<String, Object> data = new HashMap<>(1);
            data.put("feedback", feedback);
            if (feedback != null) {
                return ResponseUtil.getSuccessResponse("获取成功", data);
            }
        }
        return ResponseUtil.getFailResponse("获取失败", new HashMap<>(16));
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseDTO updateFeedback(int id, int status) {
        if (status == 2 || status == 3) {
            feedbackService.updateFeedback(id, status);
            return ResponseUtil.getSuccessResponse("修改成功", new HashMap<>(16));
        } else {
            return ResponseUtil.getFailResponse("修改失败", new HashMap<>(16));
        }
    }

}
