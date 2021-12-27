package com.tjetc.service;

import com.tjetc.entity.User;

import java.util.List;

/**
 * @author solang
 * @date 2021-12-27 10:07
 */
public interface UserService {
    User selectByName(String username);

    List<String> selectPermissionsByUsername(String username);

    String selectAvatarByUsername(String username);

}
