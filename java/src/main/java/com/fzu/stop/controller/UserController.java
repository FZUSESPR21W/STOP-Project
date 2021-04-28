package com.fzu.stop.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.fzu.stop.Util.WechatUtil;
import com.fzu.stop.pojo.FeedbackDO;
import com.fzu.stop.pojo.ResponseDTO;
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
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final int INVALID = 40029;
    private static final int OFTEN = 45011;
    @PostMapping("/login")
    @ResponseBody
    public ResponseDTO login(@RequestBody JSONObject data) throws IOException {
        JSONObject jsonObject= data.getJSONObject("data");
        //获得登录凭证code
        String code=jsonObject.getString("code");
        //登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
        JSONObject code2Session = WechatUtil.getCode2Session(code);
        Integer errorCode =code2Session.getInteger("errcode");

        //errcode为0表示请求成功
        if(errorCode==0){
            //将获得的openid 作为loginId
            StpUtil.setLoginId(code2Session.getString("openid"));
            // 获取当前会话的token值,将该token值作为skey传给小程序端作为会话的维护
            String skey=StpUtil.getTokenValue();
            Map map=new HashMap();
            map.put("skey",skey);
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

}
