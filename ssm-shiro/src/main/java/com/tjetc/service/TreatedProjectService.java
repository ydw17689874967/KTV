package com.tjetc.service;

import com.github.pagehelper.PageInfo;

/**
 * @author solang
 * @date 2021-12-27 10:09
 */
public interface TreatedProjectService {
    Long findRoleId(String username);

    PageInfo findTreatedProject(String username, Integer intKey, Integer pageNo, Integer pageSize);
}
