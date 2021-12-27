package com.tjetc.service;


import com.github.pagehelper.PageInfo;
import com.tjetc.entity.ApprovalProject;

import java.util.List;


public interface ApprovalProjectService {
    //查看所有的审批项目
//    List<ApprovalProject> findApprovalProject();

    // 分页显示所有数据
    PageInfo findApprovalProject(Integer pageSize, Integer PageNum);

    //更新项目审核状态，会计已拨款更改状态为项目进行中
    boolean updateApprovalStatus(Integer id);

    //模糊查询
    List<ApprovalProject> findTitleLike(String title);
}
