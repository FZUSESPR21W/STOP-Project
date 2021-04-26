package com.fzu.stop.service;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.SaSecureUtil;
import com.fzu.stop.dao.AdminDao;
import com.fzu.stop.pojo.AdminDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 11139
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Value("${sa.salt}")
    String salt;
    @Override
    public AdminDO login(String username, String password) {
        AdminDO admin = adminDao.getAdminByUsername(username);
        String newPassword = SaSecureUtil.md5BySalt(password, salt);
        if(newPassword.equals(admin.getPassword())){
            return admin;
        } else {
            return null;
        }
    }
}
