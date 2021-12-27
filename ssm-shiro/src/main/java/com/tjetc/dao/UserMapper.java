package com.tjetc.dao;

import com.tjetc.entity.User;
import com.tjetc.entity.vo.UserPermissionsVo;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    UserPermissionsVo selectPermissionsByUsername(String username);

    int updateById(User user);

    Long selectByUserInfoId(Long id);

    int insertUser(User user);

    int updateUserState(Long id);

    String selectAvatarByUsername(String username);
}