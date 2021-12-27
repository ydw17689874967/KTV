package com.tjetc.service.impl;

import com.tjetc.dao.GroupMemberMapper;
import com.tjetc.entity.vo.MemberInfoVo;
import com.tjetc.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {
@Autowired
private  GroupMemberMapper groupMemberMapper;

    @Override
    public List<MemberInfoVo> selectAllMembers(Long id) {
        return groupMemberMapper.selectMemberInfo1(id);
    }

    @Override
    public Long selectGroupId(String username) {
        return groupMemberMapper.selectGroupIdByUsername(username);
    }

    @Override
    public List<MemberInfoVo> selectTaskState(Long id) {
        return groupMemberMapper.selectTaskState(id);
    }
}
