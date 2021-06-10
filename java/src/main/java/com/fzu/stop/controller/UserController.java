package com.fzu.stop.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fzu.stop.pojo.FeedbackDO;
import com.fzu.stop.service.FeedbackService;
import com.fzu.stop.util.ResponseUtil;
import com.fzu.stop.util.StpUserUtil;
import com.fzu.stop.util.WechatUtil;
import com.fzu.stop.pojo.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 梁达毅
 * @author 宋家锐
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final int INVALID = 40029;
    private static final int OFTEN = 45011;
    @Autowired
    FeedbackService feedbackService;
    @PostMapping("/login")
    @ResponseBody
    public ResponseDTO login(@RequestBody JSONObject jsonObject) throws IOException {
        //获得登录凭证code
        String code=jsonObject.getString("code");
        //登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
        JSONObject code2Session = WechatUtil.getCode2Session(code);
        Integer errorCode =code2Session.getInteger("errcode");

        //errcode为0表示请求成功
        if(errorCode==0){
            //将获得的openid 作为loginId
            StpUserUtil.setLoginId(code2Session.getString("openid"));
            // 获取当前会话的token值,将该token值作为skey传给小程序端作为会话的维护
            String satoken=StpUserUtil.getTokenValue();
            Map map=new HashMap();
            map.put("tokenKey","satoken");
            map.put("tokenValue",satoken);
            return new ResponseDTO(errorCode,"登录成功",map);
        }
        else if(errorCode==-1){
            return new ResponseDTO(errorCode,"系统繁忙，此时请开发者稍候再试",new HashMap<>());
        }
        else if(errorCode==INVALID){
            return new ResponseDTO(errorCode,"code 无效",new HashMap<>());
        }
        else if(errorCode==OFTEN){
            return new ResponseDTO(errorCode,"频率限制，每个用户每分钟100次",new HashMap<>());
        }
        return null;
    }
    @SaCheckLogin(key = "user")
    @PostMapping("/complain")
    @ResponseBody
    public ResponseDTO complain(@RequestBody JSONObject req){
        FeedbackDO feedbackDO = JSONObject.parseObject(JSON.toJSONString(req),FeedbackDO.class);
        feedbackDO.setOpenId((String) StpUserUtil.getLoginId());
        boolean flag = feedbackService.addFeedback(feedbackDO);
        if(flag){
            return ResponseUtil.getSuccessResponse("反馈成功", new HashMap<>(1));
        }
        return ResponseUtil.getFailResponse("反馈失败",new HashMap<>(1));
    }

}
