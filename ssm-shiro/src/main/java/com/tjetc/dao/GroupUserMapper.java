package com.tjetc.dao;

import com.tjetc.entity.GroupUser;
import java.util.List;

public interface GroupUserMapper {
    int insert(GroupUser record);

    List<GroupUser> selectAll();

    Long selectGroupIdByUsername(String username);

    int delete(Long id);
}