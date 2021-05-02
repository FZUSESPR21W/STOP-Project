package com.fzu.stop.controller;

import com.fzu.stop.Util.ResponseUtil;
import com.fzu.stop.pojo.NoticeDO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.NoticeService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武雍易
 */
@Controller
@Validated
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @ResponseBody
    @PostMapping("/publish")
    public ResponseDTO publishNotice(@NotNull(message = "title不能为空") String title, @NotNull(message = "content不能为空") String content
            , @NotNull(message = "top不能为空") Boolean top, @Range(min = 0,max = 1,message = "非法的状态") Integer status){
        NoticeDO notice = new NoticeDO(title,content,top?1:0,status);
        noticeService.publishNotice(notice);
        return ResponseUtil.getSuccessResponse("发布成功",new HashMap<>());
    }

    @ResponseBody
    @GetMapping("/detail")
    public ResponseDTO getNoticeDetail(@Min(value = 1,message = "错误的id") Integer id){
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
    @GetMapping("/get_notice_list")
    public ResponseDTO getNoticeList(@Min(value = 1,message = "错误的page") Integer page, @Min(value = 1,message = "错误的limit") Integer limit
            , String keyword, Integer orderBy, Boolean top){
        List<NoticeDO> noticeList = noticeService.getNoticeList(page,limit,keyword,orderBy,top);
        Map<String, Object> data = new HashMap<>(1);
        data.put("noticeList",noticeList);
        return ResponseUtil.getSuccessResponse("",data);
    }

    @ResponseBody
    @PostMapping("/update")
    public ResponseDTO updateNotice(@Min(value = 1,message = "错误的id") Integer id, String title, String content
            , @NotNull(message = "top不能为空") Boolean top, @Range(min = 0,max = 1,message = "非法的状态") Integer status){
        NoticeDO notice = new NoticeDO(id,title,content,top?1:0,status);
        noticeService.updateNotice(notice);
        return ResponseUtil.getSuccessResponse("更新成功",new HashMap<>());
    }
}