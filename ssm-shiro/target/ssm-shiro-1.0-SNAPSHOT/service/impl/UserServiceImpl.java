package com.tjetc.service.impl;

import com.tjetc.dao.UserMapper;
import com.tjetc.entity.User;
import com.tjetc.entity.vo.UserPermissionsVo;
import com.tjetc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByName(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<String> selectPermissionsByUsername(String username) {
        UserPermissionsVo userPermissionsVo = userMapper.selectPermissionsByUsername(username);
        return userPermissionsVo.getPermissionNames();
    }

    @Override
    public String selectAvatarByUsername(String username) {
        String avatar = userMapper.selectAvatarByUsername(username);
        return avatar;
    }
}
