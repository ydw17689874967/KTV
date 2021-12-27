package com.tjetc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.dao.ApprovalProjectMapper;
import com.tjetc.entity.ApprovalProject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author solang
 * @date 2021-12-27 11:13
 */
public class ApprovalProjectService implements com.tjetc.service.ApprovalProjectService {
    @Autowired
    private ApprovalProjectMapper approvalProjectMapper;

    @Override
    public PageInfo findApprovalProject(Integer pageSize, Integer pageNum) {


    }

    @Override
    public boolean updateApprovalStatus(Integer id) {
        return approvalProjectMapper.updateApprovalStatus(id);
    }

    @Override
    public List<ApprovalProject> findTitleLike(String title) {
        return approvalProjectMapper.selectLikeTitle();
    }
}
