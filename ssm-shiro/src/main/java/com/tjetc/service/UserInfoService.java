package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.entity.User;
import com.tjetc.entity.UserInfo;
import com.tjetc.entity.vo.UserUserInfoVo;

/**
 * @author solang
 * @date 2021-12-27 10:12
 */
public interface UserInfoService {
    PageInfo<UserUserInfoVo> findUserInfoByRole(String role, int page, int limit);

    UserUserInfoVo findUserInfoById(Long id);


    boolean updateUserInfo(User user, UserInfo userInfo);

    boolean addUserInfo(User user, UserInfo userInfo);


    boolean updateUserState(Long id);


}
