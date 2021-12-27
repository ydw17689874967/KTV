package com.tjetc.dao;

import com.tjetc.entity.UserRole;
import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    List<UserRole> selectAll();
    Long selectRoleByUser(Long userId);
}