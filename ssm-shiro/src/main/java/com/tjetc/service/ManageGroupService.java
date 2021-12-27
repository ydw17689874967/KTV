package com.tjetc.service;

import com.tjetc.entity.vo.GroupManageVo;
import com.tjetc.entity.vo.UserUserInfoVo;

import java.util.List;

/**
 * @author solang
 * @date 2021-12-27 10:05
 */
public interface ManageGroupService {
    List<GroupManageVo> findAllGroup();

    List<UserUserInfoVo> selectMemberInfoByGId(Long id);

}
