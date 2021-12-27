package com.tjetc.service;

import com.tjetc.entity.User;

import java.util.List;

public interface UserService {
    User selectByName(String username);

    List<String> selectPermissionsByUsername(String username);

    String selectAvatarByUsername(String username);
}
