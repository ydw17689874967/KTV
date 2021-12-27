package com.tjetc.service;

import com.tjetc.entity.Admin;
import com.tjetc.entity.vo.AdminPermissionVo;

import java.util.List;

public interface AdminService {
    Admin findByUsername(String username);

    AdminPermissionVo findVoByUsername(String username);

    List<Admin> findAll();

    List<String> findPermissionNamesByUsername(String username);
}
