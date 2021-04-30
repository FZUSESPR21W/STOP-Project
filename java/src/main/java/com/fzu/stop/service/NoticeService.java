package com.fzu.stop.service;

import com.fzu.stop.pojo.NoticeDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 武雍易
 */
@Service
public interface NoticeService {

    /**
     * 添加公告
     * @param notice 公告
     */
    void publishNotice(NoticeDO notice);

    /**
     * 查看公告
     * @param id 公告编号
     * @return 公告
     */
    NoticeDO getNoticeDetail(Integer id);

    /**
     * 获取公告列表，可以进行分页查找，也可以模糊搜索关键词
     * @param page 页面下标
     * @param limit 每页显示条数
     * @param keyword 关键词
     * @param orderBy 0代表按发布时间，1代表按更新时间
     * @param top 判断是否置顶
     * @return 公告列表
     */
    List<NoticeDO> getNoticeList(Integer page, Integer limit, String keyword, Integer orderBy, Boolean top);

    /**
     * 更新公告
     * @param notice 公告
     */
    void updateNotice(NoticeDO notice);
}