package com.fzu.stop.service;

import com.fzu.stop.pojo.NoticeDO;
import org.springframework.stereotype.Service;

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


}
