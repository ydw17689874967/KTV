package com.tjetc.service;

import com.tjetc.entity.vo.GroupManageVo;
import com.tjetc.entity.vo.UserUserInfoVo;

import java.util.List;

public interface ManageGroupService {
    /**
     * 查找所有小组
     * @return
     */
    List<GroupManageVo> findAllGroup();

    /**
     * 查找所有组员
     * @param id
     * @return
     */
    List<UserUserInfoVo> selectMemberInfoByGId(Long id);
}
