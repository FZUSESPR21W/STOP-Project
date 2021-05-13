package com.fzu.stop.satoken;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StpInterfaceImplTest {
    @Autowired
    StpInterfaceImpl stpInterface;
    @Test
    void getPermissionList() {
        List<String> list = stpInterface.getPermissionList("1", null);
        if(list!=null){
            for (String s : list) {
                System.out.println(s);
            }
        }

    }

    @Test
    void getRoleList() {
    }
}