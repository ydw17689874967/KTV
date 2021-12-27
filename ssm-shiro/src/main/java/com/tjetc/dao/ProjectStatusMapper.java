package com.tjetc.dao;

import com.tjetc.entity.ProjectStatus;
import java.util.List;

public interface ProjectStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectStatus record);

    ProjectStatus selectByPrimaryKey(Integer id);

    List<ProjectStatus> selectAll();

    int updateByPrimaryKey(ProjectStatus record);
}