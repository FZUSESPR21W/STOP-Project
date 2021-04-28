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
    public ResponseDTO getFeedbackList(@RequestParam int page,@RequestParam int limit) {
        Object feedbackList = feedbackService.getFeedbackList(page, limit);
        Map<String,Object> data = new HashMap(limit);
        data.put("feedbackList",feedbackList);

        return ResponseUtil.getSuccessResponse("获取成功", data);
    }

    @PostMapping("/detail")
    @ResponseBody
    public ResponseDTO getFeedback(@RequestParam int id){
        Object feedback = feedbackService.getFeedback(id);
        Map<String,Object> data = new HashMap<>(1);
        data.put("feedback",feedback);

        return ResponseUtil.getSuccessResponse("获取成功",data);
    }
}
