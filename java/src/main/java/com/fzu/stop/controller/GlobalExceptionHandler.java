package com.fzu.stop.controller;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梁达毅
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 在当前类每个方法进入之前触发的操作
     * @param request
     * @throws IOException
     */
   @ModelAttribute
   public void get(HttpServletRequest request) throws IOException {
   }


    /**
     * 全局异常拦截（拦截项目中的所有异常）
     * @param e
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @ExceptionHandler
    public String handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 不同异常返回不同状态码
        String message = "";
        if (e instanceof NotLoginException) {
            // 如果是未登录异常
            NotLoginException ee = (NotLoginException) e;
            message="登录失败"+ee.getMessage();
        } else if(e instanceof NotRoleException) {
            // 如果是角色异常
            NotRoleException ee = (NotRoleException) e;
            message="无此角色：" + ee.getRole();
        } else if(e instanceof NotPermissionException) {
            // 如果是权限异常
            NotPermissionException ee = (NotPermissionException) e;
            message="无此权限：" + ee.getCode();
        } else if(e instanceof DisableLoginException) {
            // 如果是被封禁异常
            DisableLoginException ee = (DisableLoginException) e;
            message="账号被封禁：" + ee.getDisableTime() + "秒后解封";
        } else {
            // 普通异常, 输出：500 + 异常信息
            message=e.getMessage();
        }

        response.setStatus(403);
        // 返回给前端
        return message;
    }


    /**
     * 全局异常拦截（拦截项目中的NotLoginException异常）
     * @param nle
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    @ResponseBody
	@ExceptionHandler(NotLoginException.class)
	public String handlerNotLoginException(NotLoginException nle, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 判断场景值，定制化异常信息
		String message = "";
		if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
			message = "未提供token";
		}
		else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
			message = "token无效";
		}
		else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
			message = "token已过期";
		}
		else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
			message = "token已被顶下线";
		}
		else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
			message = "token已被踢下线";
		}
		else {
			message = "当前会话未登录";
		}
        response.setStatus(401);
		// 返回给前端
		return message;
	}

}
