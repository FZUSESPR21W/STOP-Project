package com.fzu.stop.service;

import com.fzu.stop.dao.NoticeDao;
import com.fzu.stop.pojo.NoticeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
