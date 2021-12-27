package com.tjetc.service;

import com.tjetc.entity.vo.MemberInfoVo;

import java.util.List;

public interface GroupMemberService {
    List<MemberInfoVo> selectAllMembers(Long id);

    Long selectGroupId(String username);

    List<MemberInfoVo> selectTaskState(Long id);
}
