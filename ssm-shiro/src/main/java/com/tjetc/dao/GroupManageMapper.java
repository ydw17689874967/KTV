package com.tjetc.dao;

import com.tjetc.entity.vo.GroupManageVo;
import com.tjetc.entity.vo.UserUserInfoVo;

import java.util.List;

public interface GroupManageMapper {
    List<GroupManageVo> selectAllGroup();

    List<UserUserInfoVo> selectMemberInfoByGId(Long id);
}
