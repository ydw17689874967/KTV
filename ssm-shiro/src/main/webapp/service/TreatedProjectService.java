package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.entity.vo.ProjectGroupInfo;

import java.util.List;

public interface TreatedProjectService {
    PageInfo findTreatedProject(String username, Integer key, Integer pageNo, Integer pageNum);
    Long findRoleId(String username);
}
