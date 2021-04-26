package com.fzu.stop.service;

import com.fzu.stop.pojo.AdminDO;

/**
 * @author ldy
 */
public interface AdminService {
    /**
     * 登陆验证
     * @param username
     * @param password
     * @return
     */
    AdminDO login(String username, String password);
}
