package com.tjetc.service.impl;

import com.tjetc.dao.AdminMapper;
import com.tjetc.dao.PermissionMapper;
import com.tjetc.entity.Admin;
import com.tjetc.entity.Permission;
import com.tjetc.entity.vo.AdminPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Admin findByUsername(String username) {
        return adminMapper.selectByUsername(username);
    }

    @Override
    public AdminPermissionVo findVoByUsername(String username) {
        return adminMapper.selectVoByUsername(username);
    }

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectAll();
    }

    @Override
    public List<String> findPermissionNamesByUsername(String username) {
        //根据用户名查询用户的权限信息，如果查询的用户 是root，那么单独查询所有的权限信息  非root用户 查询权限信息 多表联查
        AdminPermissionVo adminPermissionVo = findVoByUsername(username);
        //权限名称列表
        List<String> permissionNames = adminPermissionVo.getPermissionNames();
        if (adminPermissionVo.getAdminRoot()) { //判断username是root用户
            //如果是root用户查询所有的权限信息
            List<Permission> permissions = permissionMapper.selectAll();
            for (Permission permission : permissions) {
                permissionNames.add(permission.getPermissionName());
            }
        }
        return permissionNames;
    }
}
