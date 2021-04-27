package com.fzu.stop.controller;

import com.fzu.stop.Util.ResponseUtil;
import com.fzu.stop.pojo.NoticeDO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 武雍易
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @ResponseBody
    @RequestMapping("/publish")
    public ResponseDTO publishNotice(String title, String content, Boolean top, Integer status){
        if (title == null || content == null || top == null || status == null) {
            return ResponseUtil.getSuccessResponse("发布失败",new HashMap<>());
        }
        NoticeDO notice = new NoticeDO();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setTop(top?1:0);
        notice.setStatus(status);
        noticeService.publishNotice(notice);
        return ResponseUtil.getSuccessResponse("发布成功",new HashMap<>());
    }

    @ResponseBody
    @RequestMapping("/detail")
    public ResponseDTO getNoticeDetail(Integer id){
        NoticeDO notice = noticeService.getNoticeDetail(id);
        if (notice != null) {
            Map<String, Object> map = new HashMap<>(7);
            map.put("id",notice.getId());
            map.put("title",notice.getTitle());
            map.put("content",notice.getContent());
            map.put("top",notice.getTop());
            map.put("status",notice.getStatus());
            map.put("publish_time",notice.getPublishTime());
            map.put("update_time",notice.getUpdateTime());
            return ResponseUtil.getSuccessResponse("查看成功",map);
        } else {
            return ResponseUtil.getFailResponse("查看失败",new HashMap<>());
        }
    }
}
