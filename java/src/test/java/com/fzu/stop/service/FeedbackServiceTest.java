package com.fzu.stop.service;

import com.fzu.stop.pojo.FeedbackDO;
import com.fzu.stop.util.WechatUtil;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class FeedbackServiceTest {
    @Autowired
    FeedbackService feedbackService;

    List<FeedbackDO> feedbackDOList;


    @BeforeEach
    void setUp() {
        feedbackDOList = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFeedbackList() {
        feedbackDOList = feedbackService.getFeedbackList(1,5);
        Assertions.assertEquals(5,feedbackDOList.size());
    }

    @Test
    void getFeedback() {
        FeedbackDO feedbackDO;
        feedbackDO = (FeedbackDO) feedbackService.getFeedback(2);
        Assertions.assertEquals("李四",feedbackDO.getUsername());
    }

    @Test
    void updateFeedback() {
        int flag = feedbackService.updateFeedback(2,2);
        Assertions.assertEquals(1,flag);
    }

    @Test
    void addFeedback() {
//        String openid = "oi_5x5V2QZEgJdCatc94ucZ04ErI";
//        try {
//            System.out.println(WechatUtil.getUserInfo(openid));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FeedbackDO feedbackDO = new FeedbackDO();
//        feedbackDO.setContent("test");
//        feedbackDO.setUsername("李明");
//        feedbackDO.setOpenId("test123");
//        Assertions.assertTrue(feedbackService.addFeedback(feedbackDO));
    }
}