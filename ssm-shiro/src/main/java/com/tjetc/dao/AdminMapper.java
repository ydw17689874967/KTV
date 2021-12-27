package com.tjetc.dao;

import com.tjetc.entity.Admin;
import com.tjetc.entity.vo.AdminPermissionVo;

import java.util.List;

public interface AdminMapper {
    Admin selectByUsername(String username);

    AdminPermissionVo selectVoByUsername(String username);

    List<Admin> selectAll();
}
