package com.fzu.stop.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.fzu.stop.dao.AdminDao;
import com.fzu.stop.pojo.AdminDO;
import com.fzu.stop.pojo.AuthorityDo;
import com.fzu.stop.pojo.RoleDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StpInterfaceImpl implements StpInterface {
    @Autowired
    AdminDao adminDao;

    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        List<RoleDO> roles = adminDao.listRolesByAdminId((Integer) loginId);
        Set<String> permissionSet =new HashSet<>();
        for (RoleDO role : roles) {
            List<AuthorityDo> authorities = adminDao.listAuthoritiesByRoleId(role.getId());
            for (AuthorityDo authority : authorities) {
                permissionSet.add(authority.getAuthorityName());
            }
        }
        List<String> permissionList = new ArrayList<>();
        permissionList.addAll(permissionSet);
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        List<RoleDO> roles = adminDao.listRolesByAdminId((Integer) loginId);
        List<String> roleList =new ArrayList<>();
        for (RoleDO role : roles) {
            roleList.add(role.getName());
        }
        return roleList;
    }
}
