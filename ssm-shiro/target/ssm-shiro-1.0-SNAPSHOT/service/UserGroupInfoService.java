package com.tjetc.service;

import com.tjetc.entity.Task;
import com.tjetc.entity.vo.UserGroupInfoVo;

import java.util.List;

public interface UserGroupInfoService {
    /**
     * 根据组长username获取组员信息
     *
     * @return
     */
    List<UserGroupInfoVo> getUserInfoOfGroup();

    /**
     * 添加任务
     */
    Boolean addTask(Task task, Long uid);
}
