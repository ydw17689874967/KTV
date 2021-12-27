package com.tjetc.service;

import com.tjetc.entity.Task;
import com.tjetc.entity.vo.UserGroupInfoVo;

import java.util.List;

/**
 * @author solang
 * @date 2021-12-27 10:11
 */
public interface UserGroupInfoService {
    List<UserGroupInfoVo> getUserInfoOfGroup();

    Boolean addTask(Task task, Long valueOf);
}
