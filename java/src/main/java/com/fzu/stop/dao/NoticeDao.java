package com.fzu.stop.dao;

import com.fzu.stop.pojo.NoticeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author 武雍易
 */
@Mapper
public interface NoticeDao {


    /**
     * 新增公告
     * @param notice 公告
     */
    void insertNotice(NoticeDO notice);

    /**
     * 通过公告编号获取公告
     * @param id 公告编号
     * @return 公告
     */
    NoticeDO selectNoticeById(Integer id);

    /**
     * 获取公告列表，可以进行分页查找，也可以模糊搜索关键词
     * @param start 开始
     * @param limit 长度
     * @param keyword 关键词
     * @param orderBy 0代表按发布时间，1代表按更新时间
     * @param top 判断是否置顶
     * @return 公告列表
     */
    List<NoticeDO> selectAllNotices(Integer start, Integer limit, String keyword, Integer orderBy, Boolean top);

    /**
     * 更新公告
     * @param notice 公告
     */
    void updateNoticeById(NoticeDO notice);

    /**
     * 删除公告
     * @param id 公告id
     */
    void deleteNoticeById(Integer id);

}
