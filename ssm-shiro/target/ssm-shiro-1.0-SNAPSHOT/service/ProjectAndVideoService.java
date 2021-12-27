package com.tjetc.service;

import com.tjetc.entity.vo.ProjectAndVideoVo;

public interface ProjectAndVideoService {
    /**
     * 修改项目和视频信息
     * @return
     */
    Long updateProject(ProjectAndVideoVo projectAndVideoVo);

    /**
     * 插视频数据
     */
    Long updateVideo(ProjectAndVideoVo projectAndVideoVo);

    /**
     * 查找单个project
     * @param id
     * @return
     */
    ProjectAndVideoVo selectOneProject(Long id);

    Long updateState(Long id);

}
