package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.entity.vo.ProjectGroupInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface UntreatedProjectService {
    PageInfo findUntreatedProject(String username, Integer key, Integer pageNo, Integer pageSize);
    Long findRoleId(String username);
    Boolean updatePassProjectState(String username, Integer key,Long pid);
    Boolean updateRefusalProjectState(String username, Integer key,  Long pid);
    Boolean updateProjectLastUpdateTime(LocalDateTime lastUpdateTime,Long pid);
}
