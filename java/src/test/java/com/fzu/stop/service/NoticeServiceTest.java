package com.fzu.stop.service;

import com.fzu.stop.pojo.NoticeDO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    NoticeService noticeService;

    List<NoticeDO> noticeTestList;

    @Before
    public void publishNotice() {
        noticeTestList = new ArrayList<>(1);
        noticeTestList.add(new NoticeDO("test1","test1",true,0));
        noticeTestList.add(new NoticeDO("test2","test2",false,1));
        noticeService.publishNotice(noticeTestList.get(0));
    }

    @After
    public void deleteNotice() {
        noticeService.deleteNotice(noticeTestList.get(0).getId());
        noticeService.deleteNotice(noticeTestList.get(1).getId());
    }

    @Test
    public void getNoticeList() {
        List<NoticeDO> noticeDOList = noticeService.getNoticeList(1,10,"test",null,null);
        Assertions.assertEquals(2,noticeDOList.size());
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