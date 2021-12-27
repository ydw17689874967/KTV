package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.ApprovalProject;
import com.tjetc.service.ApprovalProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/approval")
public class ApprovalProjectController {
    @Autowired
    private ApprovalProjectService approvalProjectService;

    @RequestMapping("/findapprovalp")
    @ResponseBody      // 将Java对象转为json格式的数据
    public JsonResult findApprovalProj(@RequestParam(name = "pageSize") Integer pageSize,
                                        @RequestParam(name = "pageNum") Integer pageNum){

        System.out.println("pageSize = " + pageSize);
        System.out.println("pageNum = " + pageNum);
        PageInfo pageInfo = approvalProjectService.findApprovalProject(Integer.valueOf(pageSize),
                Integer.valueOf(pageNum));
        return new JsonResult(0,"查询成功",pageInfo);
    }

    @RequestMapping("/modifyStatus")
    @ResponseBody
    public JsonResult modifyApprovalStatus(@RequestParam("id") Integer id){
        boolean bl = approvalProjectService.updateApprovalStatus(id);
        if (bl){
            return new JsonResult(0,"",true);
        }else {
            return new JsonResult(1,"",false);
        }
    }

    @RequestMapping("/cha")
    @ResponseBody
    public JsonResult chaxun(@RequestParam("title") String title){
        List<ApprovalProject> titleLike = approvalProjectService.findTitleLike(title);
        return new JsonResult(0,"查询成功",titleLike);
    }
}
