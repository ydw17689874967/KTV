package com.tjetc.service;

import com.github.pagehelper.PageInfo;

import java.time.LocalDateTime;

/**
 * @author solang
 * @date 2021-12-27 10:10
 */
public interface UntreatedProjectService {
    Long findRoleId(String username);

    PageInfo findUntreatedProject(String username, Integer intKey, Integer pageNo, Integer pageSize);

    Boolean updatePassProjectState(String username, Integer intKey, Long pid);

    void updateProjectLastUpdateTime(LocalDateTime now, Long pid);


    Boolean updateRefusalProjectState(String username, Integer intKey, Long pid);

}
