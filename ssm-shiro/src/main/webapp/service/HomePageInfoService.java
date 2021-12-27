package com.tjetc.service;

import org.springframework.stereotype.Service;

@Service
public interface HomePageInfoService {
    /**
     * 查找用户角色Id
     * @param username
     * @return
     */
    Long findUserRoleId(String username);

    /**
     * 根据用户状态查询项目数量
     * @param state
     * @return
     */
    Long findCountByState(int state);

    
}
