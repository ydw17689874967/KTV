package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.entity.ApprovalProject;

import java.util.List;

/**
 * @author solang
 * @date 2021-12-27 9:57
 */
public interface ApprovalProjectService {
    PageInfo findApprovalProject(Integer pageSize, Integer pageNum);

    boolean updateApprovalStatus(Integer id);

    List<ApprovalProject> findTitleLike(String title);
}
