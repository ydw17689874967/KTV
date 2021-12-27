package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.entity.Project;
import com.tjetc.entity.vo.ProjectInfoVo;

/**
 * @author solang
 * @date 2021-12-27 10:08
 */
public interface ProjectService {
    Boolean addApplyProject(Project project);

    PageInfo selectProjectAll(Integer valueOf, Integer valueOf1);

    ProjectInfoVo selectProjectById(Long id);

    Integer updateProjectStateById(Long id);


}
