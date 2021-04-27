package com.fzu.stop.config;


import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author 梁达毅
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
            //登陆验证--拦截所有路由，并排除/admin/login用于开放登陆
            SaRouterUtil.match(Arrays.asList("/**"),Arrays.asList("/admin/login","/user/login"), ()-> StpUtil.checkLogin());

            // 角色认证 --
            SaRouterUtil.match("/feedback/**", () -> StpUtil.checkRoleOr("feedback-admin", "super-admin"));
            SaRouterUtil.match("/notice/**", () -> StpUtil.checkRoleOr("notice-admin", "super-admin"));
            SaRouterUtil.match("/data/**", () -> StpUtil.checkRoleOr("data-admin", "super-admin"));

            // 权限认证 -- 不同模块, 校验不同权限
            SaRouterUtil.match("/feedback/**", () -> StpUtil.checkPermission("user"));
        })).addPathPatterns("/**");
    }
}
