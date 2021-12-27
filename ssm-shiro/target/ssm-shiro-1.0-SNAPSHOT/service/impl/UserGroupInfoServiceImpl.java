package com.tjetc.service.impl;

import com.tjetc.dao.UserGroupInfoMapper;
import com.tjetc.entity.Task;
import com.tjetc.entity.vo.UserGroupInfoVo;
import com.tjetc.service.UserGroupInfoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupInfoServiceImpl implements UserGroupInfoService {
    @Autowired
    private UserGroupInfoMapper userGroupInfoMapper;

    @Override
    public List<UserGroupInfoVo> getUserInfoOfGroup() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        List<UserGroupInfoVo> userinfos = userGroupInfoMapper.selectGroupUserInfoByXzzz(username);
        return userinfos;
    }

    @Override
    public Boolean addTask(Task task, Long uid) {
        userGroupInfoMapper.addTask(task);
        Integer integer = userGroupInfoMapper.addTaskAndId(task.getId(), uid);
        if (integer > 0) {
            return true;
        }
        return false;
    }
}
