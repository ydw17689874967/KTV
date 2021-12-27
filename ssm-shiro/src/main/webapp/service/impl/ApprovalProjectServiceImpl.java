package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.dao.ApprovalProjectMapper;
import com.tjetc.entity.ApprovalProject;
import com.tjetc.service.ApprovalProjectService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.List;

@Service
public class ApprovalProjectServiceImpl implements ApprovalProjectService {

    @Autowired
    private ApprovalProjectMapper approvalProjectMapper;


 /*   @Override
    public List<ApprovalProject> findApprovalProject() {
        return approvalProjectMapper.selectApprovalProject();
    }*/

    /**
     * 分页显示所有的数据
     * @param pageSize  每页显示的数据的条数
     * @param PageNum    当前的页码
     * @return
     */
    @Override
    public PageInfo findApprovalProject(Integer pageSize, Integer PageNum) {
        PageHelper.startPage(PageNum,pageSize);
        List<ApprovalProject> approvalProjects = approvalProjectMapper.selectApprovalProject();
        PageInfo pageInfo = new PageInfo(approvalProjects);
        return pageInfo;
    }

    @Override
    public boolean updateApprovalStatus(Integer id) {
        return approvalProjectMapper.updateApprovalStatus(id);
    }

    @Override
    public List<ApprovalProject> findTitleLike(String title) {
        return approvalProjectMapper.selectLikeTitle(title);
    }
}
