package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.dao.ProjectMapper;
import com.tjetc.dao.UserMapper;
import com.tjetc.dao.UserRoleMapper;
import com.tjetc.entity.vo.ProjectGroupInfo;
import com.tjetc.entity.vo.UserPermissionsVo;
import com.tjetc.service.TreatedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TreatedProjectServiceImpl implements TreatedProjectService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ProjectMapper projectMapper;
    List<ProjectGroupInfo> projects =null;
    @Override
    public PageInfo findTreatedProject(String username, Integer key,Integer pageNo,Integer pageNum) {
        Long roleId = findRoleId(username);
        if (roleId==1){
            if (key==0){
                List<Integer> status = Arrays.asList(4,5,6,7,8);
                PageHelper.startPage(pageNo,pageNum);
                projects = projectMapper.selectProjectAndGroupByStatus(status);


            }else {
                List<Integer> status = Arrays.asList(11,12);
                PageHelper.startPage(pageNo,pageNum);
                projects = projectMapper.selectProjectAndGroupByStatus(status);
            }
        }
        else if (roleId==2){
            if (key==0){
                List<Integer> status = Arrays.asList(2,3,4,5,6,7);
                PageHelper.startPage(pageNo,pageNum);
                projects = projectMapper.selectProjectAndGroupByStatus(status);
            }else {
                List<Integer> status = Arrays.asList(9,10,11,12);
                PageHelper.startPage(pageNo,pageNum);
                projects = projectMapper.selectProjectAndGroupByStatus(status);
            }
        }
        else {
                List<Integer> status = Arrays.asList(5,7,8,9,10,11,12);
                PageHelper.startPage(pageNo,pageNum);
                projects = projectMapper.selectProjectAndGroupByStatus(status);
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
}
