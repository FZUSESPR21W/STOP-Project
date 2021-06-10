package com.fzu.stop.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fzu.stop.util.ResponseUtil;
import com.fzu.stop.pojo.FeedbackDO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.FeedbackService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 宋家锐
 */
@Controller
@Validated
@RequestMapping("/feedback")
@SaCheckLogin()
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @GetMapping("/get_feedback_list")
    @ResponseBody
    public ResponseDTO getFeedbackList(@Min(value = 1,message = "错误的id") Integer page,@Min(value = 1,message = "错误的id") Integer limit) {
        List<FeedbackDO> feedbackList = feedbackService.getFeedbackList(page, limit);
        Integer total = feedbackService.getCount();
        if (feedbackList.size() > 0) {
            Map<String, Object> data = new HashMap<>(limit);
            data.put("feedbackList", feedbackList);
            data.put("total", total);
            return ResponseUtil.getSuccessResponse("获取成功", data);
        }
        return ResponseUtil.getFailResponse("获取失败", new HashMap<>(16));
    }

    @GetMapping("/detail")
    @ResponseBody
    public ResponseDTO getFeedback(@Min(value = 1,message = "错误的id") Integer id) {
        Object feedback = feedbackService.getFeedback(id);
        Map<String, Object> data = new HashMap<>(1);
        data.put("feedback", feedback);
        if (feedback != null) {
            return ResponseUtil.getSuccessResponse("获取成功", data);
        }
        return ResponseUtil.getFailResponse("获取失败", new HashMap<>(16));
    }
    @SaCheckPermission("feedback-update")
    @GetMapping("/update")
    @ResponseBody
    public ResponseDTO updateFeedback(@NotNull @Min(value = 1,message = "错误的id") Integer id,@NotNull @Range(min = 2,max = 3,message = "非法的状态") Integer status) {
            feedbackService.updateFeedback(id, status);
            return ResponseUtil.getSuccessResponse("修改成功", new HashMap<>(16));
    }

}
