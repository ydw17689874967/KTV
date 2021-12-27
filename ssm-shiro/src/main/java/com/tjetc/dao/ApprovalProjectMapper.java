package com.tjetc.dao;

import com.tjetc.entity.ApprovalProject;
import com.tjetc.entity.vo.ProjectInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApprovalProjectMapper {
    //查看审批的项目
//    List<ApprovalProject> selectApprovalProject();

//    List<ApprovalProject> selectApprovalProject(@Param(value = "username") String username);


    List<ApprovalProject> selectApprovalProject();

    //更改审批项目状态为已拨款的为项目正在进行
    boolean updateApprovalStatus(Integer id);

    //根据标题模糊查询
    List<ApprovalProject> selectLikeTitle(@Param("title") String title);
}
