package com.tjetc.dao;

import com.tjetc.entity.Project;
import com.tjetc.entity.vo.ProjectGroupInfo;
import com.tjetc.entity.vo.ProjectInfoVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    Project selectByPrimaryKey(Integer id);

    List<Project> selectAll();

    int updateByPrimaryKey(Project record);


    List<ProjectGroupInfo> selectProjectAndGroupByStatus(List<Integer> status);
    List<ProjectGroupInfo> selectProjectAndGroupByState(Integer state);
    int passProjectState(@Param("state") Integer state, @Param("pid") Long pid);
    int updateProjectLastUpdateTime(@Param("lastUpdateTime") LocalDateTime lastUpdateTime, @Param("pid") Long pid);

    /**
     * 查询项目 project_status>=7
     *
     * @return
     */
    List<ProjectInfoVo> getAllProject(@Param(value = "username") String username);

    /**
     * 根据ID项目详情
     */

    ProjectInfoVo getProjectById(Long id);

    /**
     * 提交项目，更改项目状态为8
     */

    Integer updateProjectById(@Param("id") Long id, @Param("lastUpdateTime") LocalDateTime lastUpdateTime);

}