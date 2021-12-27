package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.dao.*;
import com.tjetc.entity.GroupUser;
import com.tjetc.entity.User;
import com.tjetc.entity.UserInfo;
import com.tjetc.entity.UserRole;
import com.tjetc.entity.vo.UserUserInfoVo;
import com.tjetc.exception.CustomerException;
import com.tjetc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserAndUserInfoMapper userInfoForRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private GroupUserMapper groupUserMapper;

    @Override
    public PageInfo<UserUserInfoVo> findUserInfoByRole(String role,int page,int limit) {
        switch (role) {
            case "AC": {
                PageHelper.startPage(page,limit);
                List<UserUserInfoVo> userUserInfoVos = userInfoForRoleMapper.selectUserInfoByRole(3L);
                PageInfo<UserUserInfoVo> userUserInfoVoPageInfo = new PageInfo<>(userUserInfoVos);
                return userUserInfoVoPageInfo;
            }
            case "PM": {
                PageHelper.startPage(page,limit);
                List<UserUserInfoVo> userUserInfoVos = userInfoForRoleMapper.selectUserInfoByRole(2L);
                PageInfo<UserUserInfoVo> userUserInfoVoPageInfo = new PageInfo<>(userUserInfoVos);
                return userUserInfoVoPageInfo;
            }
            case "GL": {
                PageHelper.startPage(page,limit);
                List<UserUserInfoVo> userUserInfoVos = userInfoForRoleMapper.selectUserInfoByRole(4L);
                PageInfo<UserUserInfoVo> userUserInfoVoPageInfo = new PageInfo<>(userUserInfoVos);
                return userUserInfoVoPageInfo;
            }
            case "GC": {
                PageHelper.startPage(page,limit);
                List<UserUserInfoVo> userUserInfoVos = userInfoForRoleMapper.selectUserInfoByRole(5L);
                PageInfo<UserUserInfoVo> userUserInfoVoPageInfo = new PageInfo<>(userUserInfoVos);
                return userUserInfoVoPageInfo;
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public UserUserInfoVo findUserInfoById(Long id) {
        return userInfoForRoleMapper.selectUserInfoById(id);
    }

    @Override
    public boolean updateUserInfo(User user, UserInfo userInfo) {
        User userBl = userMapper.selectByUsername(user.getUsername());
        if (userBl != null && userBl.getId()!=user.getId()){
            return false;
        }
        int i1 = userMapper.updateById(user);
        Long user_info_id = userMapper.selectByUserInfoId(user.getId());
        userInfo.setId(user_info_id);
        int i2 = userInfoMapper.updateById(userInfo);
        if (i2<=0 && i1<=0) {
            throw new CustomerException("用户信息更新失败");
        }
        return true;
    }

    @Override
    public boolean addUserInfo(User user, UserInfo userInfo) {
        User userBl = userMapper.selectByUsername(user.getUsername());
        if (userBl != null){
            return false;
        }
        int i1 = userInfoMapper.insertUserInfo(userInfo);
        if(i1<=0){
            throw new CustomerException("增加员工信息失败");
        }
        user.setUserInfoId(userInfo.getId());
        int i2 = userMapper.insertUser(user);
        if (i2<=0) {
            throw new CustomerException("增加员工信息失败");
        }
        int i3 = userRoleMapper.insert(new UserRole(user.getId(), 5L));
        if (i3<=0) {
            throw new CustomerException("增加员工信息失败");
        }
        int i4 = groupUserMapper.insert(new GroupUser(1L, user.getId()));
        if (i4<=0) {
            throw new CustomerException("增加员工信息失败");
        }
        return true;
    }

    @Override
    public boolean updateUserState(Long id) {
        int i = userMapper.updateUserState(id);
        int i1 = groupUserMapper.delete(id);
        return i>0 && i1>0;
    }
}
