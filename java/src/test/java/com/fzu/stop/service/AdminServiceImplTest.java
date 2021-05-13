package com.fzu.stop.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.fzu.stop.pojo.AdminDO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminService adminService;
    @Value("${sa.salt}")
    String salt;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login1() {
        AdminDO admin=new AdminDO();
        admin.setUsername("admin");
        String password= SaSecureUtil.md5BySalt("123456",salt);
        admin.setPassword(password);
        AdminDO adminDO = adminService.login("admin", "123456");
        assertTrue(adminDO.getUsername().equals(admin.getUsername())&&adminDO.getPassword().equals(admin.getPassword()));

    }
    @Test
    void login2() {
        AdminDO adminDO = adminService.login("ADMIN", "123456");
        assertTrue(adminDO==null);

    }
    @Test
    void login3() {
        AdminDO adminDO = adminService.login("admin", "654321");
        assertTrue(adminDO==null);

    }

}