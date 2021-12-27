package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.entity.User;
import com.tjetc.entity.UserInfo;
import com.tjetc.entity.vo.UserUserInfoVo;

import java.util.List;

public interface UserInfoService {

    PageInfo<UserUserInfoVo> findUserInfoByRole(String role, int page, int limit);

    UserUserInfoVo findUserInfoById(Long id);

    boolean updateUserInfo(User user, UserInfo userInfo);

    boolean addUserInfo(User user,UserInfo userInfo);

    boolean updateUserState(Long id);
}
