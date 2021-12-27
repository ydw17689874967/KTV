package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.entity.Project;
import com.tjetc.entity.vo.ProjectInfoVo;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {
    Boolean addApplyProject(Project project);

    PageInfo selectProjectAll(Integer pageSize, Integer PageNum);

    ProjectInfoVo selectProjectById(Long id);

    public Integer updateProjectStateById(Long id);
}
