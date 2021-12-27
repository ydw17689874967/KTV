package com.tjetc.dao;

import com.tjetc.entity.vo.UserUserInfoVo;

import java.util.List;

public interface UserAndUserInfoMapper {
    List<UserUserInfoVo> selectUserInfoByRole(Long roleId);

    UserUserInfoVo selectUserInfoById(Long id);

}
