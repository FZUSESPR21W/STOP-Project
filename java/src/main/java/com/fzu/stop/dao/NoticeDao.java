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
     * 获取公告列表
     * @return 公告集合
     */
    List<NoticeDO> selectAllNotices();

    /**
     * 更新公告
     * @param notice 公告
     */
    void updateNotice(NoticeDO notice);

}
