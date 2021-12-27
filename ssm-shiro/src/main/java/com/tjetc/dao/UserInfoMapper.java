package com.tjetc.dao;

import com.tjetc.entity.UserInfo;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);

    int updateById(UserInfo userInfo);

    int insertUserInfo(UserInfo userInfo);
}