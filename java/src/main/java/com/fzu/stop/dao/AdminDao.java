package com.fzu.stop.dao;

import com.fzu.stop.pojo.AdminDO;
import com.fzu.stop.pojo.AuthorityDO;
import com.fzu.stop.pojo.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 梁达毅
 */
@Mapper
public interface AdminDao {
    /**
     * 通过管理员用户名获得管理员用户
     * @param username
     * @return
     */
    AdminDO getAdminByUsername(String username);

    /**
     * 通过管理员id获得管理员用户
     * @param id
     * @return
     */
    AdminDO getAdminById(Integer id);

    /**
     * 通过管理员id获得角色列表
     * @param adminId
     * @return
     */
    List<RoleDO> listRolesByAdminId(Integer adminId);

    /**
     * 通过角色id获得权限列表
     * @param roleId
     * @return
     */
    List<AuthorityDO> listAuthoritiesByRoleId(Integer roleId);



}
