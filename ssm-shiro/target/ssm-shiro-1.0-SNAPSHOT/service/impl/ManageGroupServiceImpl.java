package com.tjetc.service.impl;

import com.tjetc.dao.GroupManageMapper;
import com.tjetc.entity.vo.GroupManageVo;
import com.tjetc.entity.vo.UserUserInfoVo;
import com.tjetc.service.ManageGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageGroupServiceImpl implements ManageGroupService {
    @Autowired
    private GroupManageMapper manageMapper;
    @Override
    public List<GroupManageVo> findAllGroup() {
        return manageMapper.selectAllGroup();
    }

    @Override
    public List<UserUserInfoVo> selectMemberInfoByGId(Long id) {
        return manageMapper.selectMemberInfoByGId(id);
    }
}
