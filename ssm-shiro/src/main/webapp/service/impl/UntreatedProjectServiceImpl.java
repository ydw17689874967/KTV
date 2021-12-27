package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.dao.ProjectMapper;
import com.tjetc.dao.UserMapper;
import com.tjetc.dao.UserRoleMapper;
import com.tjetc.entity.vo.ProjectGroupInfo;
import com.tjetc.entity.vo.UserPermissionsVo;
import com.tjetc.service.UntreatedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UntreatedProjectServiceImpl implements UntreatedProjectService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ProjectMapper projectMapper;
    List<ProjectGroupInfo> projects =null;
    @Override
    public PageInfo findUntreatedProject(String username, Integer key, Integer pageNo, Integer pageSize) {
        Long roleId = findRoleId(username);
        if (roleId==1){
            if (key==0) {
                PageHelper.startPage(pageNo,pageSize);
                projects = projectMapper.selectProjectAndGroupByState(3);
            }else {
                PageHelper.startPage(pageNo,pageSize);
                projects = projectMapper.selectProjectAndGroupByState(10);
            }
        }else if (roleId==2){
            if (key==0) {
                PageHelper.startPage(pageNo,pageSize);
                projects = projectMapper.selectProjectAndGroupByState(1);
            }else {
                PageHelper.startPage(pageNo,pageSize);
                projects = projectMapper.selectProjectAndGroupByState(8);
            }
        }else {
            PageHelper.startPage(pageNo,pageSize);
            projects = projectMapper.selectProjectAndGroupByState(6);
        }
        PageInfo pageInfo = new PageInfo(projects);
        return pageInfo;
    }

    @Override
    public Long findRoleId(String username) {
        UserPermissionsVo userPermissionsVo = userMapper.selectPermissionsByUsername(username);
        Long userId = userPermissionsVo.getUserId();
        Long roleId = userRoleMapper.selectRoleByUser(userId);
        return roleId;
    }

    @Override
    public Boolean updatePassProjectState(String username, Integer key, Long pid) {
        Long roleId = findRoleId(username);
        if (roleId==1){
            if (key==0){
                int i = projectMapper.passProjectState(6, pid);
                if (i>0){
                    return true;
                }else {
                    return false;
                }
            }else {
                int i = projectMapper.passProjectState(12, pid);
                if (i>0){
                    return true;
                }else {
                    return false;
                }
            }
        }else if (roleId==2){
            if (key==0){
                int i = projectMapper.passProjectState(3, pid);
                if (i>0){
                    return true;
                }else {
                    return false;
                }
            }else {
                int i = projectMapper.passProjectState(10, pid);
                if (i>0){
                    return true;
                }else {
                    return false;
                }
            }
        }else {
            int i = projectMapper.passProjectState(5, pid);
            if (i>0){
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public Boolean updateRefusalProjectState(String username, Integer key, Long pid) {
        Long roleId = findRoleId(username);
        if (roleId==1){
            if (key==0){
                int i = projectMapper.passProjectState(4, pid);
                if (i>0){
                    return true;
                }else {
                    return false;
                }
            }else {
                int i = projectMapper.passProjectState(11, pid);
                if (i>0){
                    return true;
                }else {
                    return false;
                }
            }
        }else{
            if (key==0){
                int i = projectMapper.passProjectState(2, pid);
                if (i>0){
                    return true;
                }else {
                    return false;
                }
            }else {
                int i = projectMapper.passProjectState(9, pid);
                if (i>0){
                    return true;
                }else {
                    return false;
                }
            }
        }
    }

    @Override
    public Boolean updateProjectLastUpdateTime(LocalDateTime lastUpdateTime, Long pid) {
        int i = projectMapper.updateProjectLastUpdateTime(lastUpdateTime, pid);
        if (i>0){
            return true;
        }else {
         return false;
        }
    }
}
