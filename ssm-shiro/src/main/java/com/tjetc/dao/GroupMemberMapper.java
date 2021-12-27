package com.tjetc.dao;

import com.tjetc.entity.vo.MemberInfoVo;

import java.util.List;

public interface GroupMemberMapper {
    List<MemberInfoVo> selectMemberInfo(Long id);

    Long selectGroupIdByUsername(String username);

    List<MemberInfoVo> selectMemberInfo1(Long id);

    List<MemberInfoVo> selectTaskState(Long id);
}
