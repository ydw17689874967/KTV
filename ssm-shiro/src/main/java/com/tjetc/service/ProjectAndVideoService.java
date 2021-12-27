package com.tjetc.service;

import com.tjetc.entity.vo.ProjectAndVideoVo;

/**
 * @author solang
 * @date 2021-12-27 10:07
 */
public interface ProjectAndVideoService {
    ProjectAndVideoVo selectOneProject(Long id);

    Long updateProject(ProjectAndVideoVo pV);

    Long updateVideo(ProjectAndVideoVo pV);

    Long updateState(Long id);

}
