package com.tjetc.dao;

import com.tjetc.entity.vo.ProjectAndVideoVo;

public interface ProjectAndVideoMapper {
    /**
     * 修改项目和视频信息
     * @return
     */
    Long updateProjectAndVideo(ProjectAndVideoVo projectAndVideoVo);

    Long updateVideo(ProjectAndVideoVo projectAndVideoVo);

    Long updateState(Long id);

    /**
     * 查找单个project
     * @param id
     * @return
     */
    ProjectAndVideoVo selectOneProject(Long id);

    /**
     * 插入video的数据到表中
     * @param projectAndVideoVo
     * @return
     */
    Boolean insertVideo(ProjectAndVideoVo projectAndVideoVo);


}
