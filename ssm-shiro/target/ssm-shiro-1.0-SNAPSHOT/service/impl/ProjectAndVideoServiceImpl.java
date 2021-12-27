package com.tjetc.service.impl;

import com.tjetc.dao.ProjectAndVideoMapper;
import com.tjetc.entity.vo.ProjectAndVideoVo;
import com.tjetc.service.ProjectAndVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectAndVideoServiceImpl implements ProjectAndVideoService {
    @Autowired
    private ProjectAndVideoMapper projectAndVideoMapper;

    @Override
    public Long updateProject(ProjectAndVideoVo projectAndVideoVo) {
        return projectAndVideoMapper.updateProjectAndVideo(projectAndVideoVo);
    }

    @Override
    public Long updateVideo(ProjectAndVideoVo projectAndVideoVo) {
        return projectAndVideoMapper.updateVideo(projectAndVideoVo);
    }

    @Override
    public ProjectAndVideoVo selectOneProject(Long id) {
        return projectAndVideoMapper.selectOneProject(id);
    }

    @Override
    public Long updateState(Long id) {
        return projectAndVideoMapper.updateState(id);
    }

}
