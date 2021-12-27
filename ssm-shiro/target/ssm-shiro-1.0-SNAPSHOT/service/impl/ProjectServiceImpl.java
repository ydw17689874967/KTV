package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.dao.GroupUserMapper;
import com.tjetc.dao.ProjectMapper;
import com.tjetc.entity.Project;
import com.tjetc.entity.vo.ProjectInfoVo;
import com.tjetc.service.ProjectService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private GroupUserMapper groupUserMapper;


    @Override
    public Boolean addApplyProject(Project project) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Long groupId = groupUserMapper.selectGroupIdByUsername(username);
        project.setGroupId(groupId);
        project.setCreateTime(LocalDateTime.now());
        project.setLastUpdateTime(LocalDateTime.now());
        project.setState(1);
        int insert = projectMapper.insert(project);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public PageInfo selectProjectAll(Integer pageSize, Integer pageNum) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectInfoVo> allProject = projectMapper.getAllProject(username);
        PageInfo pageInfo = new PageInfo(allProject);
        return pageInfo;
    }

    @Override
    public ProjectInfoVo selectProjectById(Long id) {
        ProjectInfoVo projectInfoVo = projectMapper.getProjectById(id);
        return projectInfoVo;
    }

    public Integer updateProjectStateById(Long id) {
        Integer integer = projectMapper.updateProjectById(id, LocalDateTime.now());
        return integer;
    }
}
