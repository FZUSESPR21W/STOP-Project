package com.fzu.stop.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.fzu.stop.util.ResponseUtil;
import com.fzu.stop.pojo.AdminDO;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author 梁达毅
 */
@Controller
@Validated
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Operation(description = "登录验证")
    @ApiResponses({
            @ApiResponse(responseCode ="200",description = "成功"),
            @ApiResponse(responseCode = "400",description = "请求参数没填好")
    })
    @PostMapping("/login")
    @ResponseBody
    public ResponseDTO login(@RequestBody AdminDO adminDO){
        String username=adminDO.getUsername();
        String password=adminDO.getPassword();
        AdminDO admin = adminService.login(username, password);
        if(admin!=null){
            StpUtil.setLoginId(admin.getId());
            return ResponseUtil.getSuccessResponse("登陆成功",new HashMap<>());
        } else {
            return ResponseUtil.getFailResponse("登陆失败",new HashMap<>());
        }
    }

    @Operation(description = "登出账号")
    @ApiResponses({
            @ApiResponse(responseCode ="200",description = "成功")
    })
    @PostMapping("/logout")
    @ResponseBody
    public ResponseDTO logout(){
        StpUtil.logout();
        return ResponseUtil.getSuccessResponse("登出成功",new HashMap<>());
    }
}
