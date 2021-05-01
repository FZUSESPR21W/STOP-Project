package com.fzu.stop.service;

import com.fzu.stop.dao.NoticeDao;
import com.fzu.stop.pojo.NoticeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 武雍易
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeDao noticeDao;

    @Override
    public void publishNotice(NoticeDO notice) {
       noticeDao.insertNotice(notice);
    }

    @Override
    public NoticeDO getNoticeDetail(Integer id) {
        return noticeDao.selectNoticeById(id);
    }

    @Override
    public List<NoticeDO> getNoticeList(Integer page, Integer limit, String keyword, Integer orderBy, Boolean top) {
        Integer start = (page - 1) * limit;
        return noticeDao.selectAllNotices(start,limit,keyword,orderBy,top);
    }

    @Override
    public void updateNotice(NoticeDO notice) {
        noticeDao.updateNotice(notice);
    }
}
