package com.tjetc.dao;

import com.tjetc.entity.PermissionRole;
import java.util.List;

public interface PermissionRoleMapper {
    int insert(PermissionRole record);

    List<PermissionRole> selectAll();
}