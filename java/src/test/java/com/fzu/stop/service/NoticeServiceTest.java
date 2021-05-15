package com.fzu.stop.service;

import com.fzu.stop.pojo.NoticeDO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    NoticeService noticeService;

    List<NoticeDO> noticeTestList;

    @BeforeEach
    public void publishNotice() {
        noticeTestList = new ArrayList<>(1);
        noticeTestList.add(new NoticeDO("test1","test1",true,0));
        noticeTestList.add(new NoticeDO("test2","test2",false,1));
        noticeService.publishNotice(noticeTestList.get(0));
    }

    @AfterEach
    public void deleteNotice() {
        noticeService.deleteNotice(noticeTestList.get(0).getId());
        noticeService.deleteNotice(noticeTestList.get(1).getId());
    }

    @Test
    public void getNoticeList() {
        List<NoticeDO> noticeDOList = noticeService.getNoticeList(1,10,"test",null,null);
        Assertions.assertTrue(noticeDOList!=null);
    }


    @Test
    public void getNoticeDetail() {
        NoticeDO notice = noticeService.getNoticeDetail(noticeTestList.get(0).getId());
        Assertions.assertEquals("test1",notice.getTitle());
    }

    @Test
    public void updateNotice() {
        noticeTestList.get(0).setTitle("change");
        noticeService.updateNotice(noticeTestList.get(0));
        Assertions.assertEquals("change",noticeService.getNoticeDetail(noticeTestList.get(0).getId()).getTitle());
    }

}