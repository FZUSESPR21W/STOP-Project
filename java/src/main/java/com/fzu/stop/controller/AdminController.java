package com.fzu.stop.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.fzu.stop.Util.ResponseUtil;
import com.fzu.stop.pojo.AdminDO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author ldy
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("/login")
    @ResponseBody
    public ResponseDTO login(String username,String password){
        AdminDO admin = adminService.login(username, password);
        if(admin!=null){
            StpUtil.setLoginId(admin.getId());
            return ResponseUtil.getSuccessResponse("登陆成功",new HashMap<>());
        } else {
          return ResponseUtil.getFailResponse("登陆失败",new HashMap<>());
        }
    }
    @GetMapping("/visit")
    @ResponseBody
    public String visit(){
        return (String) StpUtil.getLoginId();
    }

}
