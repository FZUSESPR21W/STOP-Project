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
import java.util.List;
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
        if (title == null || "".equals(title) || content == null || "".equals(content) || top == null || status == null) {
            return ResponseUtil.getFailResponse("发布失败",new HashMap<>());
        }
        NoticeDO notice = new NoticeDO(title,content,top?1:0,status);
        noticeService.publishNotice(notice);
        return ResponseUtil.getSuccessResponse("发布成功",new HashMap<>());
    }

    @ResponseBody
    @RequestMapping("/detail")
    public ResponseDTO getNoticeDetail(Integer id){
        NoticeDO notice = noticeService.getNoticeDetail(id);
        if (notice != null) {
            Map<String, Object> data = new HashMap<>(1);
            data.put("notice",notice);
            return ResponseUtil.getSuccessResponse("查看成功",data);
        } else {
            return ResponseUtil.getFailResponse("查看失败",new HashMap<>());
        }
    }

    @ResponseBody
    @RequestMapping("/get_notice_list")
    public ResponseDTO getNoticeList(Integer page, Integer limit, String keyword, Integer orderBy, Boolean top){
        List<NoticeDO> noticeList = null;
        noticeList = noticeService.getNoticeList(page,limit,keyword,orderBy,top);
        Map<String, Object> data = new HashMap<>(1);
        data.put("noticeList",noticeList);
        return ResponseUtil.getSuccessResponse("",data);
    }
}