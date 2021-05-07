package com.fzu.stop.controller;

import com.fzu.stop.Util.ResponseUtil;
import com.fzu.stop.pojo.NoticeDO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.NoticeService;
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
    public ResponseDTO publishNotice(@Validated({NoticeDO.Insert.class}) NoticeDO notice){
        noticeService.publishNotice(notice);
        return ResponseUtil.getSuccessResponse("发布成功",new HashMap<>());
    }

    @ResponseBody
    @GetMapping("/detail")
    public ResponseDTO getNoticeDetail(@NotNull(message = "id不能为空") @Min(value = 1,message = "错误的id") Integer id){
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
    public ResponseDTO getNoticeList(@NotNull(message = "page不能为空") @Min(value = 1,message = "错误的page") Integer page
            , @NotNull(message = "limit不能为空") @Min(value = 1,message = "错误的limit") Integer limit
            , String keyword, @Range(min = 0,max = 1,message = "非法的排序") Integer orderBy, Boolean top){
        List<NoticeDO> noticeList = noticeService.getNoticeList(page,limit,keyword,orderBy,top);
        if (noticeList.size()>0) {
            Map<String, Object> data = new HashMap<>(1);
            data.put("noticeList", noticeList);
            return ResponseUtil.getSuccessResponse("获取成功", data);
        } else {
            return ResponseUtil.getFailResponse("获取失败", new HashMap<>());
        }
    }

    @ResponseBody
    @PostMapping("/update")
    public ResponseDTO updateNotice(@Validated({NoticeDO.Update.class}) NoticeDO notice){
        noticeService.updateNotice(notice);
        return ResponseUtil.getSuccessResponse("更新成功",new HashMap<>());
    }
}