package com.tjetc.service;

import com.tjetc.entity.vo.MemberInfoVo;

import java.util.List;

/**
 * @author solang
 * @date 2021-12-27 10:04
 */
public interface GroupMemberService {
    Long selectGroupId(String username);

    List<MemberInfoVo> selectAllMembers(Long id);

    List<MemberInfoVo> selectTaskState(Long id);
}
